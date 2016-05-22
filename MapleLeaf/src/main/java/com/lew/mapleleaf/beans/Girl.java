package com.lew.mapleleaf.beans;

/**
 * Created by richie 2016/5/22.
 */
public class Girl extends Person{
    protected int 生日 = 250;
    protected boolean 愿意嫁;
    protected boolean 愿意等;
    protected int 感情;
    protected 结局 嫁给(Person 某人){
        return 结局.在一起了;
    }

    protected boolean 愿意等(){
        return 愿意等;
    }

    protected 结局 goTo(Person otherOne){
        return 结局.掰了;
    }
}
