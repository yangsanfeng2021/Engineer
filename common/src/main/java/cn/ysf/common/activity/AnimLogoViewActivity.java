package cn.ysf.common.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;

import cn.ysf.common.R;
import cn.ysf.common.arouter.RoutePath;
import cn.ysf.common.base.BaseActivity;
import cn.ysf.common.view.AnimLogoView;

/**
 * logo动画
 */
@Route(path = RoutePath.COMMON_ACTIVITY_ANIM_LOGO)
public class AnimLogoViewActivity extends BaseActivity {
    @Override
    protected int getLayoutId() {
        return R.layout.activity_anim_logo_view;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        AnimLogoView animLogoView = findViewById(R.id.animLogoView);
        animLogoView.addOffsetAnimListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
            }
        });
        animLogoView.addGradientAnimListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
            }
        });
        animLogoView.startAnimation();
    }
}
