package cn.ysf.engineer.activity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import cn.ysf.common.arouter.RoutePath;
import cn.ysf.common.base.BaseActivity;
import cn.ysf.engineer.R;

@Route(path = RoutePath.APP_ACTIVITY_TEST)
public class TestActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RoutePath.TEST_ACTIVITY_Abb).navigation();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }
}