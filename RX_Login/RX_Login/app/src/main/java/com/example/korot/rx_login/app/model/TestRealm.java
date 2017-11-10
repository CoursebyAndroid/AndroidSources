package com.example.korot.rx_login.app.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class TestRealm extends RealmObject {
    @PrimaryKey
    @Required
    private long id;

    private int date;

    public TestRealm() {
    }

    public TestRealm(TestRealm obj) {
        this.id   = obj.getId();
        this.date = obj.getDate();
    }

    public TestRealm(long id, int date) {
        this.id = id;
        this.date = date;
    }


    @Override
    public String toString() {
        return "TestRealm{" +
                "id=" + id +
                ", date=" + date +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
