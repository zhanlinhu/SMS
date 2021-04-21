package com.sms.entity;

public class BaseCourse {
    private Integer id;//编号
    private String name;//课程名
    private String synopsis;//课程简介
    private String collegeId;//专业id;

    public BaseCourse() {
    }

    public BaseCourse(Integer id, String name, String synopsis, String collegeId) {
        this.id = id;
        this.name = name;
        this.synopsis = synopsis;
        this.collegeId = collegeId;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}
