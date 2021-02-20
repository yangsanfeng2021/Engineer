package cn.ysf.engineer;

import android.app.Application;
import android.graphics.Color;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;

import cn.ysf.common.util.ToastUtil;
import cn.ysf.common.util.UtilsInit;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {    // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
        UtilsInit.init(this);

        ToastUtil.init(getApplicationContext())//初始化
//                .setBackground() 设置背景颜色 默认灰色白字
                .setTextColor(Color.BLACK)//设置字体颜色
                .setOrientation(ToastUtil.HORIZONTAL) //设置布局
                .setTextSize(24).setPosition(ToastUtil.CENTER); //显示的位置
    }
}
