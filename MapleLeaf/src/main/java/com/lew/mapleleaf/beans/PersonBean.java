package com.lew.mapleleaf.beans;

/**
 * Created by Richie on 2017/8/19
 */
//[{"name":"张三","age":20,"sex":"GG","hobby":"20010925"},{"name":"李四","age":0,"sex":"","hobby":"20010944"}]
public class PersonBean {
    private String name;
    private int age;
    private String sex;
    private String hobby;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }
}
