package cn.ysf.common.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;

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
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                // TODO Auto-generated method stub
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.d("app", " onViewInitFinished is " + arg0);
            }

            @Override
            public void onCoreInitFinished() {
                // TODO Auto-generated method stub
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(application,  cb);
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
