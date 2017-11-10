package com.example.korot.rx_login.app.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;


public class UserRealm extends RealmObject {

    @PrimaryKey
    private long id;

    private String login;
    private String lastName;
    private String fristName;
    private String email;
    private String phone;
    private String paswword;
    private String product;
    private String sosialToken;
    private String serverToken;

    public UserRealm() {
    }

    public UserRealm(String sosialToken) {
        this.sosialToken = sosialToken;
    }

    public UserRealm(String email, String phone) {
        this.email = email;
        this.phone = phone;
    }

    public UserRealm(String phone, String email, String paswword) {
        this.phone = phone;
        this.email = email;
        this.paswword = paswword;
    }

    @Override
    public String toString() {
        return "UserRealm{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fristName='" + fristName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", paswword='" + paswword + '\'' +
                ", product='" + product + '\'' +
                ", sosialToken='" + sosialToken + '\'' +
                ", serverToken='" + serverToken + '\'' +
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
    public String getLogin() {return login;}
    public void setLogin(String login) {this.login = login;}
    public String getLastName() {return lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public String getFristName() {return fristName;}
    public void setFristName(String fristName) {this.fristName = fristName;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}
    public String getProduct() {return product;}
    public void setProduct(String product) {this.product = product;}
    public String getServerToken() {return serverToken;}
    public void setServerToken(String serverToken) {this.serverToken = serverToken;}
    public String getPaswword() {return paswword;}
    public void setPaswword(String paswword) {this.paswword = paswword;}
}
