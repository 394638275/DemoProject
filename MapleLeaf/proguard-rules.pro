-optimizationpasses 5                                                           # 指定代码的压缩级别
-dontusemixedcaseclassnames                                                     # 是否使用大小写混合
-dontskipnonpubliclibraryclasses                                                # 是否混淆第三方jar
-dontpreverify                                                                  # 混淆时是否做预校验
-verbose                                                                        # 混淆时是否记录日志
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*        # 混淆时所采用的算法

-keep public class * extends android.app.Activity                               # 保持哪些类不被混淆
-keep public class * extends android.app.Application                            # 保持哪些类不被混淆
-keep public class * extends android.app.Service                                # 保持哪些类不被混淆
-keep public class * extends android.content.BroadcastReceiver                  # 保持哪些类不被混淆
-keep public class * extends android.content.ContentProvider                    # 保持哪些类不被混淆
-keep public class * extends android.app.backup.BackupAgentHelper               # 保持哪些类不被混淆
-keep public class * extends android.preference.Preference                      # 保持哪些类不被混淆
-keep public class com.android.vending.licensing.ILicensingService              # 保持哪些类不被混淆

-keepclasseswithmembernames class * {                                           # 保持 native 方法不被混淆
    native <methods>;
}

-keepclasseswithmembers class * {                                               # 保持自定义控件类不被混淆
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);     # 保持自定义控件类不被混淆
}

-keepclassmembers class * extends android.app.Activity {                        # 保持自定义控件类不被混淆
   public void *(android.view.View);
}

-keepclassmembers enum * {                                                      # 保持枚举 enum 类不被混淆
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class * implements android.os.Parcelable {                                # 保持 Parcelable 不被混淆
  public static final android.os.Parcelable$Creator *;
}

#-keep class MyClass;                                                            # 保持自己定义的类不被混淆

#如果有引用v4包可以添加下面这行
-keep class android.support.v4.** { *; }
-keep public class * extends android.support.v4.**
-keep public class * extends android.app.Fragment
-dontwarn android.support.**

#保持自定义组件不被混淆
-keep public class * extends android.view.View {
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
    public void set*(...);
}
#保持 Serializable 不被混淆
-keepnames class * implements java.io.Serializable

#保持 Serializable 不被混淆并且enum 类也不被混淆
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#自己项目特殊处理代码（Gson相关等等）
-keep class com.blwy.zjh.manager.entity.** {*;}
-keep class com.blwy.zjh.manager.net.** {*;}
-keep class com.blwy.zjh.manager.ui.widgets.update.** {*;}

##混淆保护自己项目的部分代码以及引用的第三方jar包library（想混淆去掉"#"）
#-libraryjars libs/umeng-analytics-v5.6.7.jar
#-libraryjars libs/nineoldandroids-2.4.0.jar
#-libraryjars libs/okhttp-3.0.0-RC1.jar
#-libraryjars libs/okio-1.6.0.jar
#-libraryjars libs/ormlite-android-4.49-SNAPSHOT.jar
#-libraryjars libs/ormlite-core-4.49-SNAPSHOT.jar
#-libraryjars libs/pgyer_sdk_2.2.2.jar

# 以libaray的形式引用的图片加载框架,不想混淆（注意，此处不是jar包形式，想混淆去掉"#"）
-keep class com.nostra13.universalimageloader.** { *; }
-keep class com.github.mikephil.charting.** { *; }

###-------- Gson 相关的混淆配置--------
-keepattributes Signature
-keepattributes *Annotation*
-keep class sun.misc.Unsafe { *; }

###--------------umeng 相关的混淆配置-----------
-keep class com.umeng.** { *; }
-keep class com.umeng.analytics.** { *; }
-keep class com.umeng.common.** { *; }
-keep class com.umeng.newxp.** { *; }

-keep public class org.codehaus.**
-keep public class java.nio.**
-keep public class android.webkit.**
-keep public class javax.**
-keep class javax.** { *; }



-dontwarn org.codehaus
-dontnote org.codehaus
-dontwarn java.nio.**
-dontnote java.nio.**
-dontwarn okio.**
-dontnote okio.**
-dontwarn com.j256.ormlite.**
-dontnote com.j256.ormlite.**
-dontwarn com.google.gson.**
-dontnote com.google.gson.**
-dontwarn android.support.v4.**
-dontnote android.support.v4.**
-dontwarn android.support.v7.**
-dontnote android.support.v7.**
-dontwarn com.blwy.zjh.manager.utils.CommonUtils
-dontnote com.blwy.zjh.manager.utils.CommonUtils
-dontwarn com.tencent.connect.**
-dontnote com.tencent.connect.**
-dontwarn com.sina.weibo.**
-dontnote com.sina.weibo.**
-dontnote com.squareup.okhttp.**
-dontwarn com.squareup.okhttp.**
-dontnote com.github.mikephil.charting.**
-dontwarn com.github.mikephil.charting.**