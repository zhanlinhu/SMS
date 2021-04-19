package com.sms.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class CascaderDto {
    @JSONField(ordinal = 1)
    private String id;
    @JSONField(ordinal = 2)
    private String name;
    @JSONField(serialize=false)
    private String collegeId;
    @JSONField(serialize=false)
    private String majorNum;
    @JSONField(ordinal = 3)
    private List<CascaderDto> children;


    public CascaderDto() {
    }

    public CascaderDto(String id, String name, String collegeId, String majorNum, List<CascaderDto> children) {
        this.id = id;
        this.name = name;
        this.collegeId = collegeId;
        this.majorNum = majorNum;
        this.children = children;
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

    public String getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(String collegeId) {
        this.collegeId = collegeId;
    }

    public List<CascaderDto> getChildren() {
        return children;
    }

    public void setChildren(List<CascaderDto> children) {
        this.children = children;
    }

    public String getMajorNum() {
        return majorNum;
    }

    public void setMajorNum(String majorNum) {
        this.majorNum = majorNum;
    }
}
