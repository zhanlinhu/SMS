package com.sms.entity;

public class Student extends User{
    private String id;//学号
    private String password;//密码
    private String name;//姓名
    private String sex;//性别
    private String grade;//班级
    private String admissionDate;//入学日期
    private String graduationDate;//毕业时间
    private Integer academicStatus;//学业状态
    private Integer majorId;//专业id

    public Student() {
    }

    public Student(String id, String password, String name, String sex, String grade, String admissionDate, String graduationDate, Integer academicStatus, Integer majorId) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.sex = sex;
        this.grade = grade;
        this.admissionDate = admissionDate;
        this.graduationDate = graduationDate;
        this.academicStatus = academicStatus;
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

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(String graduationDate) {
        this.graduationDate = graduationDate;
    }

    public Integer getAcademicStatus() {
        return academicStatus;
    }

    public void setAcademicStatus(Integer academicStatus) {
        this.academicStatus = academicStatus;
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }
}
