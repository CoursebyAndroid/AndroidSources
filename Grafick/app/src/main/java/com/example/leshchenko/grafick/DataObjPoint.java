package com.example.leshchenko.grafick;

import java.util.Date;

/**
 * Created by Root on 11.11.2017.
 */

public class DataObjPoint {
    private int distance;
    private Date date;
    private long time;
    private long secend;

    public DataObjPoint() {
    }

    public DataObjPoint(int distance) {
        this.distance = distance;
        date = new Date();
        this.time = date.getTime();
    }

    public DataObjPoint(int distance, long secend) {
        this.distance = distance;
        this.secend = secend;
    }

    @Override
    public String toString() {
        return "DataObjPoint{" +
                "distance=" + distance +
                ", date=" + date +
                ", time=" + time +
                ", secend=" + secend +
                '}';
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }


    public void setSecend(long secend) {
        this.secend = secend;
    }

    public long getSecend() {
        return secend;
    }
}
