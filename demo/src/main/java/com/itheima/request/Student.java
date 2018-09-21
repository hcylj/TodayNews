package com.itheima.request;

/**
 * Created by Lou on 2018/6/19.
 */

public class Student {
    public String name;
    public String age;
    public String sex;
    public String userid;

    public Student(String name, String age, String sex, String userid) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
