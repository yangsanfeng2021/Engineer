package cn.ysf.common.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.ysf.common.R;
import cn.ysf.common.arouter.RoutePath;
import cn.ysf.common.base.BaseActivity;
import cn.ysf.common.util.ToastUtil;

/**
 * 自定义toast
 */
@Route(path = RoutePath.COMMON_ACTIVITY_TOAST_UTIL)
public class ToastUtil1Activity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_toast_util;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListener();
    }

    private void initListener() {
        findViewById(R.id.success).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.success("成功");
            }
        });
        findViewById(R.id.warning).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.waring("警告");
            }
        });
        findViewById(R.id.error).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.error("失败");
            }
        });
        findViewById(R.id.defaultShow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.show("默认普通的");
            }
        });
        findViewById(R.id.imageShow).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.show("默认普通的",R.drawable.success);
            }
        });
    }
}
