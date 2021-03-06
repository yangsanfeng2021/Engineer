package cn.ysf.common.util;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IntDef;

import cn.ysf.common.R;

public class ToastUtil {

    private static Toast toast;
    private LinearLayout layout;

    public static ToastUtil toastUtil;
    private View view;
    private TextView tv;
    private ImageView iv;
    private int background = R.drawable.toast_bg;
    private Context mContext;
    private int textSize = 14;
    private int textColor = Color.WHITE;
    private int position = DEFAULT;


    public ToastUtil setBackground(int background) {
        this.background = background;
        return toastUtil;
    }

    public ToastUtil setTextSize(int textSize) {
        this.textSize = textSize;
        return toastUtil;
    }

    public ToastUtil setTextColor(int textColor) {
        this.textColor = textColor;
        return toastUtil;
    }

    public static ToastUtil init(Context mContext) {
        if (toastUtil == null) {
            toastUtil = new ToastUtil(mContext);
        }
        return toastUtil;
    }

    public ToastUtil(Context mContext) {
        this.mContext = mContext;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.toast_view, null);
            tv = view.findViewById(R.id.toast_custom_tv);
            iv = view.findViewById(R.id.toast_custom_iv);
            layout = view.findViewById(R.id.lin_bg);
        }
        if (toast == null) {
            toast = new Toast(mContext);
        }
        toast.setView(view);
    }

    public static void show(final String tvStr) {
        if (toastUtil == null) {
            try {
                throw new Exception("ToastUtil未实例化 请执行getInstance方法");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        toastUtil.showToastWithImg(tvStr, -1, 0);
    }

    public static void show(final String tvStr, int imageResource) {
        if (toastUtil == null) {
            try {
                throw new Exception("ToastUtil未实例化 请执行getInstance方法");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        toastUtil.showToastWithImg(tvStr, imageResource, 0);
    }

    public static void success(final String tvStr) {
        if (toastUtil == null) {
            try {
                throw new Exception("ToastUtil未实例化 请执行getInstance方法");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        toastUtil.showToastWithImg(tvStr, R.drawable.done, 1);
    }

    public static void error(final String tvStr) {
        if (toastUtil == null) {
            try {
                throw new Exception("ToastUtil未实例化 请执行getInstance方法");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        toastUtil.showToastWithImg(tvStr, R.drawable.error, 2);
    }

    public static void waring(final String tvStr) {

        if (toastUtil == null) {
            try {
                throw new Exception("ToastUtil未实例化 请执行getInstance方法");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        toastUtil.showToastWithImg(tvStr, R.drawable.warning, 3);
    }

    public static final int HORIZONTAL = LinearLayout.HORIZONTAL;
    public static final int VERTICAL = LinearLayout.VERTICAL;

    /**
     * 只能使用 {@link #HORIZONTAL} {@link #VERTICAL}
     */
    @IntDef({
            HORIZONTAL,
            VERTICAL
    }) //HORIZONTAL,VERTICAL
    public @interface Orientation {
    }

    public static final int TOP = Gravity.TOP;
    public static final int BOTTOM = Gravity.BOTTOM;
    public static final int CENTER = Gravity.CENTER;
    public static final int DEFAULT = -1;


    @IntDef({
            TOP,
            BOTTOM,
            CENTER,
            DEFAULT
    })
    public @interface Position {
    }

    public ToastUtil setOrientation(@Orientation int orientation) {
        layout.setOrientation(orientation);
        return toastUtil;
    }

    /**
     * 显示有image的toast
     */
    public void showToastWithImg(final String tvStr, final int imageResource, int type) {
        switch (type) {
            case 1:
                layout.setBackgroundResource(R.drawable.done_bg);
                tv.setTextColor(Color.WHITE);
                break;
            case 2:
                layout.setBackgroundResource(R.drawable.error_bg);
                tv.setTextColor(Color.WHITE);
                break;
            case 3:
                layout.setBackgroundResource(R.drawable.warning_bg);
                tv.setTextColor(Color.WHITE);
                break;
            default:
                layout.setBackgroundResource(background);
                tv.setTextColor(textColor);
                break;

        }
        tv.setText(TextUtils.isEmpty(tvStr) ? "" : tvStr);
        tv.setTextSize(textSize);
        if (imageResource > 0) {
            iv.setVisibility(View.VISIBLE);
            iv.setImageResource(imageResource);
        } else {
            iv.setVisibility(View.GONE);
        }
        toast.setDuration(Toast.LENGTH_SHORT);
        if (position != DEFAULT) {
            toast.setGravity(position, 0, 100);
        }
        toast.show();
    }


    public ToastUtil setPosition(@Position int position) {
        this.position = position;
        return toastUtil;
    }
}
