package cn.ysf.testlibrary;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import cn.ysf.common.BaseConstant;
import cn.ysf.common.arouter.RoutePath;
import cn.ysf.common.base.BaseActivity;

@Route(path = RoutePath.TEST_ACTIVITY_Acc)
public class AccActivity extends BaseActivity {

    @BindView(R2.id.acc)
    TextView acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        acc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "组建化测试结束", Toast.LENGTH_LONG).show();
                ARouter.getInstance().build(RoutePath.APP_ACTIVITY_MAIN)
                        .withInt(BaseConstant.KEY_INDEX, BaseConstant.INDEX_HOME_TWO)
                        .navigation();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_acc;
    }
}