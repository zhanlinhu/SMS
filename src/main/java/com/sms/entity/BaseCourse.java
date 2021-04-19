package com.sms.entity;

public class BaseCourse {
    private Integer id;//编号
    private String name;//课程名
    private String synopsis;//课程简介
    private Integer majorId;//专业id;

    public BaseCourse() {
    }

    public BaseCourse(Integer id, String name, String synopsis, Integer majorId) {
        this.id = id;
        this.name = name;
        this.synopsis = synopsis;
        this.majorId = majorId;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
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
