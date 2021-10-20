package com.sosa.retrofitej1.modelo;

public class Post {
    private int userID;
    private int id;
    private String title;
    private String body;

    public Post() {
    }

    public Post(int userID, int id, String title, String body) {
        this.userID = userID;
        this.id = id;
        this.title = title;
        this.body = body;
    }
    public Post(int userID, String title, String body) {
        this.userID = userID;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Post{" +
                "userID=" + userID +
                ", id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
