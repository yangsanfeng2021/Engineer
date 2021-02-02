package cn.ysf.engineer.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.widget.RatingBar;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;
import cn.ysf.common.arouter.RoutePath;
import cn.ysf.common.base.BaseActivity;
import cn.ysf.engineer.R;

/**
 * 星级评定
 */
@Route(path = RoutePath.APP_ACTIVITY_RATING_BAR)
public class RatingBarActivity extends BaseActivity {

    @BindView(R.id.ratingBar)
    RatingBar ratingBar;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_rating_bar;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        ratingBar.setRating(2);
    }
}
