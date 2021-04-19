package com.sms.entity;

public class Notice {
    private Integer id;//通知主键
    private String title;//标题
    private String author;//作者
    private String content;//内容
    private Integer auth;//权限 1表示仅管理员可见，2表示仅教师可见，3表示全体可见
    //@JSONField(format = "yyyy-MM-dd")
    private String newstime;//发布日期

    public Notice() {
    }

    public Notice(Integer id, String title, String author, String content, Integer auth, String newstime) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.content = content;
        this.auth = auth;
        this.newstime = newstime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getAuth() {
        return auth;
    }

    public void setAuth(Integer auth) {
        this.auth = auth;
    }

    public String getNewstime() {
        return newstime;
    }

    public void setNewstime(String newstime) {
        this.newstime = newstime;
    }
}
