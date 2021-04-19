package com.sms.dto;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class StudentVo {
    private String id;//学号|课号

    private String password;//密码
    private String name;//姓名
    private String sex;//性别
    private String major;//专业
    private String grade;//班级
    @JSONField(format = "yyyy-MM-dd")
    private Date admissionDate;//入学日期
    @JSONField(format = "yyyy-MM-dd")
    private Date graduationDate;//毕业时间
    private String academicStatus;//学业状态

    private String sId;//学号
    private Float score;//成绩
    private String result;//结果
    private String courseName;//课程名

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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getAcademicStatus() {
        return academicStatus;
    }

    public void setAcademicStatus(String academicStatus) {
        this.academicStatus = academicStatus;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "StudentVo{" +
                "id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", grade='" + grade + '\'' +
                ", admissionDate=" + admissionDate +
                ", graduationDate=" + graduationDate +
                ", academicStatus='" + academicStatus + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
