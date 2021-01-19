package cn.ysf.common.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

@SuppressLint("StaticFieldLeak")
public class UtilsInit {
    private static Context context;
    private String packageName;
    private volatile static UtilsInit instance = null;

    private UtilsInit() {
    }

    public static UtilsInit getInstance() {
        if (instance == null) {
            synchronized (UtilsInit.class) {
                if (instance == null) {
                    instance = new UtilsInit();
                }
            }
        }
        return instance;
    }

    public static void init(Application application) {
        context = application;
    }

    public Context getContext() {
        return context;
    }

    public String getPackageName() {
        return context.getPackageName();
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }
}
