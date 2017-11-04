package com.example.korot.rx_login.app.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by korot on 14.08.2017.
 */

public class User {
    @SerializedName("error")
    private String mUserError;

    @SerializedName("user_id")
    private int mUserId;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("email_2")
    private String mEmail2;

    @SerializedName("phone")
    private String mPhone;

    @SerializedName("password")
    private String mPassword;

    @SerializedName("name")
    private String mName;

    @SerializedName("surname")
    private String mSurname;

    @SerializedName("birth_day")
    private String mBirthDay;

    @SerializedName("birth_month")
    private String mBirthMonth;

    @SerializedName("sex")
    private boolean mSex;

    @SerializedName("facebook_id")
    private String mFacebookId;

    @SerializedName("google_id")
    private String mGoogleId;

    @SerializedName("single_photo")
    private String mSinglePhoto;

    @SerializedName("mid_photo")
    private String mMidPhoto;

    @SerializedName("multi_photo")
    private String mMultiPhoto;

    @SerializedName("loc_coords")
    private String mLocCoords;

    @SerializedName("time_zone")
    private String mTimeZone;

    @SerializedName("online_status")
    private boolean mOnlineStatus;

    @SerializedName("reg_date")
    private String mRegDate;

    @SerializedName("last_visit")
    private String mLastVisit;

    @SerializedName("balance")
    private String mBalance;

    @SerializedName("rate")
    private String mRate;

    @SerializedName("country")
    private String mCountry;

    @SerializedName("city")
    private String mCity;

    @SerializedName("friend")
    private String mFriend;

    @SerializedName("info")
    private String mInfo;

    @SerializedName("firebase_id")
    private String mFirebaseId;

    public String getmUserError() {
        return mUserError;
    }

    public void setmUserError(String mUserError) {
        this.mUserError = mUserError;
    }

    public User(String mUserError) {

        this.mUserError = mUserError;
    }

    public User(String mUserError, int mUserId, String mEmail, String mEmail2, String mPhone, String mPassword, String mName, String mSurname, String mBirthDay, String mBirthMonth, boolean mSex, String mFacebookId, String mGoogleId, String mSinglePhoto, String mMidPhoto, String mMultiPhoto, String mLocCoords, String mTimeZone, boolean mOnlineStatus, String mRegDate, String mLastVisit, String mBalance, String mRate, String mCountry, String mCity, String mFriend, String mInfo, String mFirebaseId) {
        this.mUserError = mUserError;
        this.mUserId = mUserId;
        this.mEmail = mEmail;
        this.mEmail2 = mEmail2;
        this.mPhone = mPhone;
        this.mPassword = mPassword;
        this.mName = mName;
        this.mSurname = mSurname;
        this.mBirthDay = mBirthDay;
        this.mBirthMonth = mBirthMonth;
        this.mSex = mSex;
        this.mFacebookId = mFacebookId;
        this.mGoogleId = mGoogleId;
        this.mSinglePhoto = mSinglePhoto;
        this.mMidPhoto = mMidPhoto;
        this.mMultiPhoto = mMultiPhoto;
        this.mLocCoords = mLocCoords;
        this.mTimeZone = mTimeZone;
        this.mOnlineStatus = mOnlineStatus;
        this.mRegDate = mRegDate;
        this.mLastVisit = mLastVisit;
        this.mBalance = mBalance;
        this.mRate = mRate;
        this.mCountry = mCountry;
        this.mCity = mCity;
        this.mFriend = mFriend;
        this.mInfo = mInfo;
        this.mFirebaseId = mFirebaseId;
    }

    @Override
    public String toString() {
        return "User{" +
                "mUserError='" + mUserError + '\'' +
                ", mUserId=" + mUserId +
                ", mEmail='" + mEmail + '\'' +
                ", mEmail2='" + mEmail2 + '\'' +
                ", mPhone='" + mPhone + '\'' +
                ", mPassword='" + mPassword + '\'' +
                ", mName='" + mName + '\'' +
                ", mSurname='" + mSurname + '\'' +
                ", mBirthDay='" + mBirthDay + '\'' +
                ", mBirthMonth='" + mBirthMonth + '\'' +
                ", mSex=" + mSex +
                ", mFacebookId='" + mFacebookId + '\'' +
                ", mGoogleId='" + mGoogleId + '\'' +
                ", mSinglePhoto='" + mSinglePhoto + '\'' +
                ", mMidPhoto='" + mMidPhoto + '\'' +
                ", mMultiPhoto='" + mMultiPhoto + '\'' +
                ", mLocCoords='" + mLocCoords + '\'' +
                ", mTimeZone='" + mTimeZone + '\'' +
                ", mOnlineStatus=" + mOnlineStatus +
                ", mRegDate='" + mRegDate + '\'' +
                ", mLastVisit='" + mLastVisit + '\'' +
                ", mBalance='" + mBalance + '\'' +
                ", mRate='" + mRate + '\'' +
                ", mCountry='" + mCountry + '\'' +
                ", mCity='" + mCity + '\'' +
                ", mFriend='" + mFriend + '\'' +
                ", mInfo='" + mInfo + '\'' +
                ", mFirebaseId='" + mFirebaseId + '\'' +
                '}';
    }

    public int getmUserId() {
        return mUserId;
    }

    public void setmUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getmEmail2() {
        return mEmail2;
    }

    public void setmEmail2(String mEmail2) {
        this.mEmail2 = mEmail2;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmSurname() {
        return mSurname;
    }

    public void setmSurname(String mSurname) {
        this.mSurname = mSurname;
    }

    public String getmBirthDay() {
        return mBirthDay;
    }

    public void setmBirthDay(String mBirthDay) {
        this.mBirthDay = mBirthDay;
    }

    public String getmBirthMonth() {
        return mBirthMonth;
    }

    public void setmBirthMonth(String mBirthMonth) {
        this.mBirthMonth = mBirthMonth;
    }

    public boolean ismSex() {
        return mSex;
    }

    public void setmSex(boolean mSex) {
        this.mSex = mSex;
    }

    public String getmFacebookId() {
        return mFacebookId;
    }

    public void setmFacebookId(String mFacebookId) {
        this.mFacebookId = mFacebookId;
    }

    public String getmGoogleId() {
        return mGoogleId;
    }

    public void setmGoogleId(String mGoogleId) {
        this.mGoogleId = mGoogleId;
    }

    public String getmSinglePhoto() {
        return mSinglePhoto;
    }

    public void setmSinglePhoto(String mSinglePhoto) {
        this.mSinglePhoto = mSinglePhoto;
    }

    public String getmMidPhoto() {
        return mMidPhoto;
    }

    public void setmMidPhoto(String mMidPhoto) {
        this.mMidPhoto = mMidPhoto;
    }

    public String getmMultiPhoto() {
        return mMultiPhoto;
    }

    public void setmMultiPhoto(String mMultiPhoto) {
        this.mMultiPhoto = mMultiPhoto;
    }

    public String getmLocCoords() {
        return mLocCoords;
    }

    public void setmLocCoords(String mLocCoords) {
        this.mLocCoords = mLocCoords;
    }

    public String getmTimeZone() {
        return mTimeZone;
    }

    public void setmTimeZone(String mTimeZone) {
        this.mTimeZone = mTimeZone;
    }

    public boolean ismOnlineStatus() {
        return mOnlineStatus;
    }

    public void setmOnlineStatus(boolean mOnlineStatus) {
        this.mOnlineStatus = mOnlineStatus;
    }

    public String getmRegDate() {
        return mRegDate;
    }

    public void setmRegDate(String mRegDate) {
        this.mRegDate = mRegDate;
    }

    public String getmLastVisit() {
        return mLastVisit;
    }

    public void setmLastVisit(String mLastVisit) {
        this.mLastVisit = mLastVisit;
    }

    public String getmBalance() {
        return mBalance;
    }

    public void setmBalance(String mBalance) {
        this.mBalance = mBalance;
    }

    public String getmRate() {
        return mRate;
    }

    public void setmRate(String mRate) {
        this.mRate = mRate;
    }

    public String getmCountry() {
        return mCountry;
    }

    public void setmCountry(String mCountry) {
        this.mCountry = mCountry;
    }

    public String getmCity() {
        return mCity;
    }

    public void setmCity(String mCity) {
        this.mCity = mCity;
    }

    public String getmFriend() {
        return mFriend;
    }

    public void setmFriend(String mFriend) {
        this.mFriend = mFriend;
    }

    public String getmInfo() {
        return mInfo;
    }

    public void setmInfo(String mInfo) {
        this.mInfo = mInfo;
    }

    public String getmFirebaseId() {
        return mFirebaseId;
    }

    public void setmFirebaseId(String mFirebaseId) {
        this.mFirebaseId = mFirebaseId;
    }
}
