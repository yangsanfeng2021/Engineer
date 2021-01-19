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
import cn.ysf.common.dialog.SignInDialog;

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
                SignInDialog signInDialog = new SignInDialog(getActivity());
                signInDialog.show();
                signInDialog.setIntegral("30");
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_acc;
    }
}