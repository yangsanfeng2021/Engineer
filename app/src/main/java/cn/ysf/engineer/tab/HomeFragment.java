package cn.ysf.engineer.tab;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import cn.ysf.common.arouter.RoutePath;
import cn.ysf.common.base.BaseFragment;
import cn.ysf.engineer.R;

/**
 * 主页界面
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.explosionTv)
    TextView explosionTv;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
    }

    private void initListener() {
        explosionTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RoutePath.COMMON_ACTIVITY_EXPLOSION).navigation();
            }
        });
    }
}
