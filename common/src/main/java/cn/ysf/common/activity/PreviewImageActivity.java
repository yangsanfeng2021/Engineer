package cn.ysf.common.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import java.util.List;
import java.util.Locale;

import cn.ysf.common.R;
import cn.ysf.common.base.BaseActivity;
import cn.ysf.common.view.webview.PhotoViewPager;

public class PreviewImageActivity extends BaseActivity {

    public static final String PREVIEW_IMAGE_ALL_URL = "allUrl";
    public static final String PREVIEW_IMAGE_CURRENT_URL = "curUrl";

    private PhotoViewPager prePhotoViewPager;
    private TextView curCountTv;

    private String curUrl;
    private List<String> imgList;
    private int curIndex;
    private PreImageViewAdapter preImageViewAdapter;

    public static void startActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, PreviewImageActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_preview_image;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getBundle() != null) {
            init();
            initListener();
        } else {
            finish();
        }
    }

    private void init() {
        curUrl = getBundle().getString(PREVIEW_IMAGE_CURRENT_URL);
        imgList = getBundle().getStringArrayList(PREVIEW_IMAGE_ALL_URL);

        prePhotoViewPager = findViewById(R.id.prePhotoViewPager);
        curCountTv = findViewById(R.id.curCountTv);
        for (int i = 0; i < imgList.size(); i++) {
            String img = imgList.get(i);
            boolean contains = curUrl.contains(img);
            if (contains) {
                curIndex = imgList.indexOf(img);
                break;
            }
        }
        if (imgList.size() > 1) {
            curCountTv.setVisibility(View.VISIBLE);
            curCountTv.setText(String.format(Locale.getDefault(), "%d/%d", curIndex + 1, imgList.size()));
        }

        preImageViewAdapter = new PreImageViewAdapter(this, imgList);
        prePhotoViewPager.setAdapter(preImageViewAdapter);
        prePhotoViewPager.setOffscreenPageLimit(imgList.size());
        prePhotoViewPager.setCurrentItem(curIndex);
    }

    private void initListener() {
        prePhotoViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                curCountTv.setText(String.format(Locale.getDefault(), "%d/%d", position + 1, imgList.size()));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
