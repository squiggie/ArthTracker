package com.arthtracker.arthtracker;

import java.util.Date;

class PainDay {
    private int mID;
    private long mDate;
    private int mFingers;
    private int mThumbs;
    private int mWrists;
    private int mElbows;
    private int mShoulders;
    private int mKnees;
    private int mAnkles;
    private int mFatigue;
    private int mStiffness;
    private int mOverall;
    private String mNotes;

    public PainDay(){
        //Constructor
    }

    public PainDay (Date date){

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

    public String getmNotes() {
        return mNotes;
    }

    public boolean save(){
        return true;
    }

    public boolean validate(){
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
}