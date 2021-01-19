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

public class Home2Fragment extends BaseFragment {

    @BindView(R.id.webViewTv)
    TextView webViewTv;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home2;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
    }

    private void initListener() {
        webViewTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RoutePath.COMMON_ACTIVITY_TEST_MAIN).navigation();
            }
        });
    }

}
