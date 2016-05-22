package com.lew.mapleleaf.beans;

/**
 * Created by richie 2016/5/22.
 */
public class Boy extends Person{
    private 礼物 玫瑰;
    protected boolean 有房;
    protected boolean 有车;
    protected int 赚钱;
    private 礼物 送什么东西;

    protected boolean 有房() {
        return 有房;
    }

    protected boolean 有车(){
        return 有车;
    }

    protected void 给Girl准备了(礼物 someThing){
        this.送什么东西 = someThing;
    }

    protected boolean 送给Girl(礼物 someThing){
        return someThing.equals(玫瑰);
    }

    protected void 拼命赚钱(){

    }
}
