package cn.ysf.engineer.tab;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;

import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import cn.ysf.common.arouter.RoutePath;
import cn.ysf.common.base.BaseFragment;
import cn.ysf.engineer.R;


public class Home1Fragment extends BaseFragment {


    public static final int REQUEST_CODE_PERMISSIONS_STORAGE = 183;

    @BindView(R.id.captureTv)
    TextView captureTv;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home1;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initListener();
    }

    private void checkStoragePermission() {
        // PackageManager.PERMISSION_GRANTED 有权限
        // PackageManager.PERMISSION_DENIED  无权限
        // 检查是否有权限
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            // 有权限, 接着检查定位权限或者其他操作
            ARouter.getInstance().build(RoutePath.COMMON_ACTIVITY_CAPTURE).navigation();
        } else {
            // 是否应该显示获取权限的解释弹窗
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("标题");
                builder.setCancelable(false);
                builder.setMessage("解释内容");
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 询问用户获取权限
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_PERMISSIONS_STORAGE);
                    }
                });
                builder.show();
            } else {
                // 询问用户获取权限
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, REQUEST_CODE_PERMISSIONS_STORAGE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // 通过请求码，判断是那一次请求，来做相应的处理
        if (requestCode == REQUEST_CODE_PERMISSIONS_STORAGE) {
            // 判断用户授予的权限是否成功
            ARouter.getInstance().build(RoutePath.COMMON_ACTIVITY_CAPTURE).navigation();
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    private void initListener() {
        captureTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkStoragePermission();
            }
        });
    }
}
