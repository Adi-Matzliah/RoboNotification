package com.exercise.temi.db.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by adi.matzliah on 07/03/2017.
 */
@Entity
public class Contact {

    @PrimaryKey
    @ColumnInfo (name = "id")
    private Integer id;

    @ColumnInfo (name = "first_name")
    private String firstName;

    @ColumnInfo (name = "last_name")
    private String lastName;

    @ColumnInfo (name = "email")
    private String email;

    @ColumnInfo (name = "gender")
    private String gender;

    @ColumnInfo (name = "phone")
    private String phone;

    @ColumnInfo (name = "address")
    private String address;

    @ColumnInfo (name = "job")
    private String job;

    @ColumnInfo (name = "avatar")
    private String avatar;

    @ColumnInfo (name = "date")
    private String date;

    @ColumnInfo (name = "messaged_at")
    private String lastMsgDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLastMsgDate() {
        return lastMsgDate;
    }

    public void setLastMsgDate(String lastMsgDate) {
        this.lastMsgDate = lastMsgDate;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}