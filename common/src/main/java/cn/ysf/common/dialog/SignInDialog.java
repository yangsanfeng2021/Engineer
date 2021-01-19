package cn.ysf.common.dialog;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import butterknife.BindView;
import cn.ysf.common.R;
import cn.ysf.common.R2;
import cn.ysf.common.base.BaseDialog;

/**
 * Vip信息弹窗
 * <p>
 * Created by yangzhizhong
 */

public class SignInDialog extends BaseDialog {

    @BindView(R2.id.bgView)
    View bgView;

    @BindView(R2.id.signInLl)
    LinearLayout signInLl;

    @BindView(R2.id.tvComplete)
    TextView tvComplete;

    @BindView(R2.id.tvIntegral)
    TextView tvIntegral;

    private int translationY;

    public SignInDialog(Context context) {
        super(context, R.style.Translucent);
        translationY = context.getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_sign_in;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListener();
    }

    @Override
    public void show() {
        super.show();

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(signInLl, "translationY", -translationY, 0);
        objectAnimator.setDuration(500);
        objectAnimator.setInterpolator(new OvershootInterpolator(1.0f));
        objectAnimator.start();

        ObjectAnimator objectAnimatorBgView = ObjectAnimator.ofFloat(bgView, "alpha", 0.0f, 1.0f);
        objectAnimatorBgView.setDuration(300);
        objectAnimatorBgView.start();
    }

    private void setListener() {
        tvComplete.setOnClickListener(v -> dismiss());
    }

    @Override
    public void dismiss() {

        ObjectAnimator objectAnimatorBgView = ObjectAnimator.ofFloat(bgView, "alpha", 1.0f, 0.0f);
        objectAnimatorBgView.setDuration(300);
        objectAnimatorBgView.start();

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(signInLl, "translationY", 0, translationY);
        objectAnimator.setDuration(500);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();

        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                SignInDialog.super.dismiss();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, @NonNull KeyEvent event) {
        return event.getKeyCode() == KeyEvent.KEYCODE_BACK || super.onKeyDown(keyCode, event);
    }

    public void setIntegral(String integral) {
        String str = "恭喜你 ! 获得 " + integral + " 积分";
        SpannableString spannableString = new SpannableString(str);
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(Color.parseColor("#ff0000"));
        int start = str.indexOf(String.valueOf(integral));
        int end = start + String.valueOf(integral).length();
        spannableString.setSpan(foregroundColorSpan, start, end, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        tvIntegral.setText(spannableString);
    }

}
