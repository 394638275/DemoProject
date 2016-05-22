package com.lew.mapleleaf.beans;

/**
 * Created by richie 2016/5/22.
 */
public class Boy extends Person{
    private String 玫瑰 = "玫瑰";
    protected boolean 有房;
    protected boolean 有车;
    protected int 赚钱;
    private String 送什么东西;

    protected boolean 有房() {
        return 有房;
    }

    protected boolean 有车(){
        return 有车;
    }

    protected void set(String someThing){
        this.送什么东西 = someThing;
    }

    protected boolean giveGirl(String someThing){
        return someThing.equals(玫瑰);
    }

    protected void 拼命赚钱(){

    }
}
