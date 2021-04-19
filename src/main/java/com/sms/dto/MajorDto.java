package com.sms.dto;

public class MajorDto {
    private Integer id;//主键id
    private String majorNum;//专业编号
    private String name ;//专业名称
    private String synopsis;//专业介绍
    private String collegeId;//学院名
    private String collegeName;//学院名

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public String getMajorNum() {
        return majorNum;
    }

    public void setMajorNum(String majorNum) {
        this.majorNum = majorNum;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
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
}
