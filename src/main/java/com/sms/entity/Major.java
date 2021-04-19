package com.sms.entity;

public class Major {
    private Integer id;//主键id
    private String majorNum;//专业编号
    private String name ;//专业名称
    private String synopsis;//专业介绍
    private String collegeId;//学院id

    public Major() {
    }


    public Major(Integer id, String majorNum, String name, String synopsis, String collegeId) {
        this.id = id;
        this.majorNum = majorNum;
        this.name = name;
        this.synopsis = synopsis;
        this.collegeId = collegeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMajorNum() {
        return majorNum;
    }

    public void setMajorNum(String majorNum) {
        this.majorNum = majorNum;
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

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }
}
