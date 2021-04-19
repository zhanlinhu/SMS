package com.sms.entity;

public class Score {
    private Integer id;//成绩主键
    private Float score;//成绩
    private String result;//考试结果 通过/挂科
    private Integer baseCourseId;//开课id
    private String sId;//学生学号

    public Score() {
    }

    public Score(Integer id, Float score, String result, Integer baseCourseId, String sId) {
        this.id = id;
        this.score = score;
        this.result = result;
        this.baseCourseId = baseCourseId;
        this.sId = sId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getResult() {
        return result;
    }

    public Integer getBaseCourseId() {
        return baseCourseId;
    }

    public void setBaseCourseId(Integer baseCourseId) {
        this.baseCourseId = baseCourseId;
    }

    public String getsId() {
        return sId;
    }

    public void setsId(String sId) {
        this.sId = sId;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getSId() {
        return sId;
    }

    public void setSId(String sId) {
        this.sId = sId;
    }
}
