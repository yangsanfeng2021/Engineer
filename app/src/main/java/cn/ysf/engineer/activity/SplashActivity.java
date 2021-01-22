package cn.ysf.engineer.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;

import cn.ysf.common.arouter.RoutePath;
import cn.ysf.common.base.BaseActivity;
import cn.ysf.engineer.R;

public class SplashActivity extends BaseActivity {

    private AppHandler appHandler;
    private int time = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListener();
    }

    private void initListener() {
        ARouter.getInstance().build(RoutePath.APP_ACTIVITY_MAIN).navigation();
        finish();

//        appHandler = new AppHandler(this, Looper.getMainLooper());
//        appHandler.sendEmptyMessage(183);

        //timer.start();
    }

    // 倒计时控件
    private final CountDownTimer timer = new CountDownTimer(3000, 1000) {
        public void onTick(long millisUntilFinished) {
            Log.i("SplashActivity", "倒计时" + millisUntilFinished / 1000 + "秒");
        }

        public void onFinish() {
            ARouter.getInstance().build(RoutePath.APP_ACTIVITY_MAIN).navigation();
            finish();
        }
    };

    @Override
    public void onHandler(Message msg) {
        super.onHandler(msg);
        if (msg.what == 183) {
            if (time > 0) {
                time--;
                appHandler.sendEmptyMessageDelayed(183, 1000);
            } else {
                ARouter.getInstance().build(RoutePath.APP_ACTIVITY_MAIN).navigation();
                finish();
            }
        }
    }
}
