package cn.ysf.common.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseDialog extends Dialog {

    private Unbinder unbinder;

    public BaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        this.setCancelable(true);
    }

    @Override
    public void dismiss() {
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        super.dismiss();
    }

    protected abstract int getLayoutId();
}
