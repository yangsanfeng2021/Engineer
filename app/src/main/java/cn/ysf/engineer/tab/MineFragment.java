package cn.ysf.engineer.tab;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import cn.ysf.common.arouter.RoutePath;
import cn.ysf.common.base.BaseFragment;
import cn.ysf.common.view.ItemView;
import cn.ysf.engineer.R;

/**
 * 我的界面
 */
public class MineFragment extends BaseFragment {

    @BindView(R.id.animLogoViewItemView)
    ItemView animLogoViewItemView;

    @BindView(R.id.testItemView)
    ItemView testItemView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initListener();
    }

    private void initListener() {
        animLogoViewItemView.setOnClickListener(v -> ARouter.getInstance().build(RoutePath.COMMON_ACTIVITY_ANIM_LOGO).navigation());

        testItemView.setOnClickListener(v -> ARouter.getInstance().build(RoutePath.APP_ACTIVITY_TEST).navigation());
    }
}
