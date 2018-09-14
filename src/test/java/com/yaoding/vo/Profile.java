package com.yaoding.vo;

import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: yaodingw
 * Date: 2018-09-14
 * Time: 10:22
 */
public class Profile implements Serializable {


    private static final long serialVersionUID = -3606284724652823191L;
    private String id;
    private String name;
    private String sex;
    private String address;
    private String phone;
    private String mark;

    public Profile(String id, String name, String sex, String address, String phone, String mark) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.address = address;
        this.phone = phone;
        this.mark = mark;
    }

    public Profile() {
    }

    public static Profile create(){
        Random random=new Random(47);
        Profile profile=new Profile();
        profile.setId(String.valueOf(random.nextInt(1000)));
        profile.setName("yaodingw");
        profile.setSex("male");
        profile.setAddress("earth");
        profile.setPhone("110");
        profile.setMark("big handsome male");
        return profile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
}
