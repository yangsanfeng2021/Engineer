package cn.ysf.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.ysf.common.R;

public class ItemView extends LinearLayout {

    private ImageView itemIconIv, arrowRightIv;
    private TextView itemTitleTv, itemContentTv;
    private View line;

    public ItemView(Context context) {
        this(context, null);
    }

    public ItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
        initAttrs(context, attrs);
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_view, this, false);
        this.addView(view);
        itemIconIv = view.findViewById(R.id.itemIconIv);
        arrowRightIv = view.findViewById(R.id.arrowRightIv);
        itemTitleTv = view.findViewById(R.id.itemTitleTv);
        itemContentTv = view.findViewById(R.id.itemContentTv);
        line = view.findViewById(R.id.line);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ItemView);
        setItemTitle(typedArray.getString(R.styleable.ItemView_itemTitle));
        setItemContent(typedArray.getString(R.styleable.ItemView_itemContent));
        setItemHintContent(typedArray.getString(R.styleable.ItemView_itemHintContent));
        setItemIcon(typedArray.getDrawable(R.styleable.ItemView_itemIcon));
        setIconVisible(typedArray.getBoolean(R.styleable.ItemView_iconVisible, true));
        setLineVisible(typedArray.getBoolean(R.styleable.ItemView_lineVisible, false));
        setArrowIconVisible(typedArray.getBoolean(R.styleable.ItemView_iconArrowVisible, true));
        typedArray.recycle();

    }

    public void setItemContent(String content) {
        itemContentTv.setText(content);
    }

    public void setItemHintContent(String content) {
        itemContentTv.setHint(content);
    }

    public void setItemIcon(Drawable drawable) {
        itemIconIv.setImageDrawable(drawable);
    }

    public void setIconVisible(boolean isVisible) {
        itemIconIv.setVisibility(isVisible ? VISIBLE : GONE);
    }

    public void setArrowIconVisible(boolean isVisible) {
        arrowRightIv.setVisibility(isVisible ? VISIBLE : GONE);
    }

    public void setLineVisible(boolean isVisible) {
        line.setVisibility(isVisible ? VISIBLE : INVISIBLE);
    }

    public void setItemTitle(String title) {
        itemTitleTv.setText(title);
    }
}
