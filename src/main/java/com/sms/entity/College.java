package com.sms.entity;

public class College {
    private String id;//学院id、编号
    private String name;//学院名称
    private String synopsis;//学院介绍

    public College() {
    }

    public College(String id, String name, String synopsis) {
        this.id = id;
        this.name = name;
        this.synopsis = synopsis;
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

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }
}
