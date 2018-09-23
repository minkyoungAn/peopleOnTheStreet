package buskinggo.seoul.com.buskinggo;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class BuskingDTO {
    private String buskerNo;
    private String photo;
    private String buskingDate;
    private String buskingTime;
    private String place;
    private String introduce;


    BuskingDTO(String buskingDate, String buskingTime, String place){
        this.buskingDate = buskingDate;
        this.buskingTime = buskingTime;
        this.place = place;
    }
    BuskingDTO(String buskerNo, String photo, String buskingDate, String buskingTime, String place, String introduce){
        this.buskerNo = buskerNo;
        this.photo = photo;
        this.buskingDate = buskingDate;
        this.buskingTime = buskingTime;
        this.place = place;
        this.introduce = introduce;

    }

    public String getBuskerNo() {
        return buskerNo;
    }

    public void setBuskerNo(String buskerNo) {
        this.buskerNo = buskerNo;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBuskingDate() {
        return buskingDate;
    }

    public void setBuskingDate(String buskingDate) {
        this.buskingDate = buskingDate;
    }

    public String getBuskingTime() {
        return buskingTime;
    }

    public void setBuskingTime(String buskingTime) {
        this.buskingTime = buskingTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }
}
