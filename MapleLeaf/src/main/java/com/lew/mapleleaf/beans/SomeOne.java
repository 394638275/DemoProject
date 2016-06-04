package com.lew.mapleleaf.beans;

/**
 * Created by liuqian 16/5/23.
 */
public class SomeOne {
    public String name;
    public int age;

    public SomeOne(String name, int age) {
        this.name = name;
        this.age = age;
    }

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
}
