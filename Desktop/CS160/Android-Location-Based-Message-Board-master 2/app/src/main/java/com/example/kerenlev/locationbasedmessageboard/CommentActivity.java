package com.example.kerenlev.locationbasedmessageboard;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CommentActivity extends AppCompatActivity {

    private static final String TAG = CommentActivity.class.getSimpleName();
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Comment> mComments = new ArrayList<Comment>();
    EditText commentInputBox;
    RelativeLayout layout;
    Button sendButton;
    private String username;
    private String landmarkName;
    public HashMap<String, String> commentThread;
    public FirebaseDatabase database = FirebaseDatabase.getInstance();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_feed);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        landmarkName = intent.getStringExtra("locationName");

        setTitle(landmarkName + ": Posts");

        layout = (RelativeLayout) findViewById(R.id.comment_layout);
        commentInputBox = (EditText) layout.findViewById(R.id.comment_input_edit_text);
        sendButton = (Button) layout.findViewById(R.id.send_button);
        mRecyclerView = (RecyclerView) findViewById(R.id.comment_recycler);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        setOnClickForSendButton();

        mComments = new ArrayList<Comment>();

        DatabaseReference landmark = database.getReference(landmarkName);

        ValueEventListener myDataListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mComments = new ArrayList<Comment>();
                commentThread = (HashMap<String, String>) dataSnapshot.getValue();
                for (DataSnapshot data : dataSnapshot.getChildren()) {
                    Log.e(TAG, "children: " + data);
                    String time = data.getKey().toString();
                    String comment = data.child("comment").getValue(String.class);
                    String user = data.child("user").getValue(String.class);
                    Comment newComment = new Comment(comment, user, new Date(time));
                    mComments.add(newComment);
                }
                setAdapterAndUpdateData();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("0", "cancelled");
            }
        };
        landmark.addValueEventListener(myDataListener);

        setAdapterAndUpdateData();
    }

    private void setOnClickForSendButton() {
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = commentInputBox.getText().toString();
                if (TextUtils.isEmpty(comment)) {
                    commentInputBox.requestFocus();
                } else {
                    commentInputBox.setText("");
                    postNewComment(comment);
                }
            }
        });
    }

    private void setAdapterAndUpdateData() {
        mAdapter = new CommentAdapter(this, mComments);
        mRecyclerView.setAdapter(mAdapter);

        if (mComments.size() == 0) {
            mRecyclerView.smoothScrollToPosition(0);
        } else {
            mRecyclerView.smoothScrollToPosition(mComments.size() - 1);
        }
    }

    private void postNewComment(String commentText) {
        Comment newComment = new Comment(commentText, username, new Date());
        mComments.add(newComment);
        DatabaseReference landmark = database.getReference(landmarkName);
        DatabaseReference time = landmark.child(String.valueOf(new Date()));
        time.child("user").setValue(username);
        time.child("comment").setValue(commentText);
        setAdapterAndUpdateData();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}
