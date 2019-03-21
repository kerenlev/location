package com.example.kerenlev.locationbasedmessageboard;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.kerenlev.locationbasedmessageboard.LocationAdapt.mContext;

public class LocationAdapt extends RecyclerView.Adapter {

    public static Context mContext;
    private ArrayList<Bears> mBears;

    public LocationAdapt (Context context, ArrayList<Bears> bears) {
        mContext = context;
        mBears = bears;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.bears_layout, parent, false);
        return new BearViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Bears bear = mBears.get(position);
        ((BearViewHolder) holder).bind(bear);
    }

    @Override
    public int getItemCount() {
        return mBears.size();
    }
}

class BearViewHolder extends RecyclerView.ViewHolder {

    public RelativeLayout mBearLayout;
    public ImageView mLocPicImageV;
    public TextView mLocNameTxtV;
    public TextView mDisTxtV;

    public BearViewHolder(View itemView) {
        super(itemView);
        mBearLayout = itemView.findViewById(R.id.bear_cell_layout);
        mLocPicImageV = mBearLayout.findViewById(R.id.picture);
        mLocNameTxtV = mBearLayout.findViewById(R.id.location);
        mDisTxtV = mBearLayout.findViewById(R.id.distance);

        mBearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDisTxtV.getText() == "less than 10 meters away") {
                    Context oContext = mContext;
                    Intent i = new Intent(oContext, CommentActivity.class);
                    i.putExtra("username", LocationActivity.username);
                    i.putExtra("locationName", mLocNameTxtV.getText());
                    oContext.startActivity(i);
                } else {
                    Toast.makeText(mContext, "Must stand within 10 meters of landmark to leave comments", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    void bind(Bears bear) {
        mLocPicImageV.setImageResource(bear.landmarkPic);
        mLocNameTxtV.setText(bear.landmarkN);
        mDisTxtV.setText(bear.location);
    }
}
