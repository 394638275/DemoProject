package com.lew.mapleleaf.beans;

/**
 * Created by richie 2016/5/22.
 */
public class 这是什么狗屁逻辑 {
    private Person 第三者;
    private int 情人节 = 214;
    private String nothing = "狗屁都不给！";
    private String 玫瑰 = "玫瑰";
    private boolean 最后还是掰了 = true;

    public boolean 最后的结局(Boy boy, Girl girl) {
        if (boy.有房() && boy.有车()) {
            boy.set(nothing);
            return girl.嫁给(boy);
        }
        if (girl.愿意等()) {
            while (!(boy.赚钱 > 100000 && girl.感情 > 8)) {
                for (int day = 1; day <= 365; day++) {
                    if (day == 情人节) {
                        if (boy.giveGirl(玫瑰))
                            girl.感情++;
                        else
                            girl.感情--;
                    }
                    if (day == girl.生日) {
                        if (boy.giveGirl(玫瑰)) {
                            girl.感情++;
                        } else {
                            girl.感情--;
                            boy.拼命赚钱();
                        }
                    }
                }
                if (boy.有房() && boy.有车()) {
                    boy.set(nothing);
                    return girl.嫁给(boy);
                }
                girl.年龄++;
                girl.感情--;
            }
            return girl.goTo(第三者);
        }
        return 最后还是掰了;
    }
}
