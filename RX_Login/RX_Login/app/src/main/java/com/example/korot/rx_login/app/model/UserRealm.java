package com.example.korot.rx_login.app.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;
/**
 * Created by Root on 07.11.2017.
 */

public class UserRealm extends RealmObject {

    @PrimaryKey
     private long id;

     private String sosialToken;

    public UserRealm() {
    }

    public UserRealm(String sosialToken) {
        this.sosialToken = sosialToken;
    }

    @Override
    public String toString() {
        return "UserRealm{" +
                "id=" + id +
                ", sosialToken='" + sosialToken + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSosialToken() {
        return sosialToken;
    }

    public void setSosialToken(String sosialToken) {
        this.sosialToken = sosialToken;
    }
}
