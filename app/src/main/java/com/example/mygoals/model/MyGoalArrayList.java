package com.example.mygoals.model;

public class MyGoalArrayList {

    String goalDate;
    String goalDescription;
    String goalTitle;
    String id;
    public MyGoalArrayList (String goalDate, String goalDescription, String goalTitle, String id) {

        this.goalDate = goalDate;
        this.goalDescription = goalDescription;
        this.goalTitle = goalTitle;
        this.id = id;
    }
  public MyGoalArrayList(){


  }

    public String getUid() {
        return id;
    }

    public void setUid(String uid) {
        this.id = uid;
    }

    public String getGoalDate() {
        return goalDate;
    }

    public void setGoalDate(String goalDate) {
        this.goalDate = goalDate;
    }

    public String getGoalDescription() {
        return goalDescription;
    }

    public void setGoalDescription(String goalDescription) {
        this.goalDescription = goalDescription;
    }

    public String getGoalTitle() {
        return goalTitle;
    }

    public void setGoalTitle(String goalTitle) {
        this.goalTitle = goalTitle;
    }
}
