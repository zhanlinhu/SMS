package com.sms.entity;

public class Teacher extends User{
    private String id;//职工号
    private String password;//密码
    private String name;//姓名
    private String sex;//性别
    private String synopsis;//简介
    private Integer majorId;//所属学院

    public Teacher() {
    }

    public Teacher(String id, String password, String name, String sex, String synopsis, Integer majorId) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.synopsis = synopsis;
        this.majorId = majorId;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}
