package cn.ysf.testlibrary;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import cn.ysf.common.arouter.RoutePath;
import cn.ysf.common.base.BaseActivity;

@Route(path = RoutePath.TEST_ACTIVITY_Abb)
public class AbbActivity extends BaseActivity {

    @BindView(R2.id.abb)
    TextView abb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        abb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RoutePath.TEST_ACTIVITY_Acc).navigation();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_abb;
    }
}