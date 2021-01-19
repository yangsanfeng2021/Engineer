package cn.ysf.common.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

import cn.ysf.common.util.CommonUtils;

/**
 * 状态栏占位控件
 * Created by zdxing on 2015/09/17.
 */
public class StatusBar extends AppCompatImageView {
    public StatusBar(Context context) {
        this(context, null);
    }

    public StatusBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 返回状态栏高度
     *
     * @return 像素
     */
    private int getStatusBarHeight(Context context) {
        int result;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        } else {
            result = CommonUtils.dp2Px(25);
        }
        return result;
    }

    @Override
    protected int getSuggestedMinimumHeight() {
        return getStatusBarHeight(getContext());
    }
}
