package com.example.kerenlev.locationbasedmessageboard;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private ArrayList<Comment> mComments;

    public CommentAdapter(Context context, ArrayList<Comment> comments) {
        mContext = context;
        mComments = comments;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.comment_layout, parent, false);
        return new CommentViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Comment comment = mComments.get(position);
        ((CommentViewHolder) holder).bind(comment);
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }
}

class CommentViewHolder extends RecyclerView.ViewHolder {

    public RelativeLayout mCommentLayout;
    public TextView mUsernameTxtV;
    public TextView mDateTxtV;
    public TextView mCommentTxtV;

    public CommentViewHolder(View itemView) {
        super(itemView);
        mCommentLayout = itemView.findViewById(R.id.comment_cell_layout);
        mUsernameTxtV = mCommentLayout.findViewById(R.id.username_text_view);
        mDateTxtV = mCommentLayout.findViewById(R.id.date_text_view);
        mCommentTxtV = mCommentLayout.findViewById(R.id.comment_text_view);
    }

    void bind(Comment comment) {
        mDateTxtV.setText(comment.username);
        mUsernameTxtV.setText("posted " + comment.elapsedTimeString() + " ago");
        mCommentTxtV.setText(comment.text);
    }
}
