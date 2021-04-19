package com.sms.entity;

public class Auth {
    private Integer id;//主键
    private String name;//接口名
    private String url;//接口url
    private Integer adminAuth;//管理员权限
    private Integer teacherAuth;//教师权限
    private Integer studentAuth;//学生权限

    public Auth() {
    }

    public Auth(Integer id, String name, String url, Integer adminAuth, Integer teacherAuth, Integer studentAuth) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.adminAuth = adminAuth;
        this.teacherAuth = teacherAuth;
        this.studentAuth = studentAuth;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getAdminAuth() {
        return adminAuth;
    }

    public void setAdminAuth(Integer adminAuth) {
        this.adminAuth = adminAuth;
    }

    public Integer getTeacherAuth() {
        return teacherAuth;
    }

    public void setTeacherAuth(Integer teacherAuth) {
        this.teacherAuth = teacherAuth;
    }

    public Integer getStudentAuth() {
        return studentAuth;
    }

    public void setStudentAuth(Integer studentAuth) {
        this.studentAuth = studentAuth;
    }
}
