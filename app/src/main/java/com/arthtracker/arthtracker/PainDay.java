package com.arthtracker.arthtracker;

import java.io.Serializable;
import java.util.Date;

class PainDay implements Serializable {
    private int mID;
    private long mDate;
    private int mFingers;
    private int mThumbs;
    private int mWrists;
    private int mElbows;
    private int mShoulders;
    private int mKnees;
    private int mAnkles;
    private int mFeet;
    private int mFatigue;
    private int mStiffness;
    private int mOverall;
    private String mNotes;
    private double mTotal = -1;
    private String mWeather;

    public PainDay(){
        //Constructor
    }

    public PainDay (Date date){

    }
    public int getmFeet() {
        return mFeet;
    }

    public void setmFeet(int mFeet) {
        this.mFeet = mFeet;
    }
    public void setmDate(long mDate) {
        this.mDate = mDate;
    }

    public void setmFingers(int mFingers) {
        this.mFingers = mFingers;
    }

    public void setmThumbs(int mThumbs) {
        this.mThumbs = mThumbs;
    }

    public void setmWrists(int mWrists) {
        this.mWrists = mWrists;
    }

    public void setmElbows(int mElbows) {
        this.mElbows = mElbows;
    }

    public void setmShoulders(int mShoulders) {
        this.mShoulders = mShoulders;
    }

    public void setmKnees(int mKnees) {
        this.mKnees = mKnees;
    }

    public void setmAnkles(int mAnkles) {
        this.mAnkles = mAnkles;
    }

    public void setmFatigue(int mFatigue) {
        this.mFatigue = mFatigue;
    }

    public void setmStiffness(int mStiffness) {
        this.mStiffness = mStiffness;
    }

    public void setmOverall(int mOverall) {
        this.mOverall = mOverall;
    }

    public void setmNotes(String mNotes) {
        this.mNotes = mNotes;
    }

    public void computeTotal() {
        //higher score is worse
        //overall has 1.5 weight
        //5*9 + 5*1.5 = 52.5 total points
        mTotal = mFingers + mThumbs + mWrists + mElbows + mShoulders + mKnees + mAnkles + mFeet + mFatigue + mStiffness;
        mTotal = mTotal + (mOverall * 1.5);
        mTotal = mTotal / 57.5;
        mTotal = mTotal * 100; //percentage
        mTotal = (double) Math.round(mTotal);
    }
    public long getmDate() {

        return mDate;
    }

    public int getmFingers() {
        return mFingers;
    }

    public int getmThumbs() {
        return mThumbs;
    }

    public int getmWrists() {
        return mWrists;
    }

    public int getmElbows() {
        return mElbows;
    }

    public int getmShoulders() {
        return mShoulders;
    }

    public int getmKnees() {
        return mKnees;
    }

    public int getmAnkles() {
        return mAnkles;
    }

    public int getmFatigue() {
        return mFatigue;
    }

    public int getmStiffness() {
        return mStiffness;
    }

    public int getmOverall() {
        return mOverall;
    }

    public double getmTotal() {

        if (mTotal < 0){
            computeTotal();
        }
        return mTotal;
    }

    public String getmNotes() {
        return mNotes;
    }

    public boolean save(){
        return true;
    }

    public boolean validate(){
        //property validation if needed
        return true;
    }

    public boolean delete (){
        return true;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmWeather() {
        return mWeather;
    }

    public void setmWeather(String mWeather) {
        this.mWeather = mWeather;
    }

}