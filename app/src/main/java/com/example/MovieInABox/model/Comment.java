package com.example.MovieInABox.model;

import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {
    @SerializedName("id")
    private Integer id;

    @SerializedName("comment")
    private String comment;

    @SerializedName("updatedAt")
    private Date updatedAt;

    @SerializedName("user")
    private User user;

    @SerializedName("createdAt")
    private Date createdAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCreatedAt() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss\ndd/MM/yyyy");
        return dateFormat.format(createdAt);
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
