package com.example.kerenlev.locationbasedmessageboard;


public class Bears {

    public Integer landmarkPic;
    public String landmarkN;
    public String location;

    Bears(Integer landmarkPicture, String landmarkName, String coordinates) {
        this.landmarkPic = landmarkPicture;
        this.landmarkN = landmarkName;
        this.location = coordinates;
    }
}
