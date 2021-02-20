package cn.ysf.common.activity

import android.os.Bundle
import cn.ysf.common.R
import cn.ysf.common.arouter.RoutePath
import cn.ysf.common.base.BaseActivity
import cn.ysf.common.util.ToastUtil
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.android.synthetic.main.activity_toast_util.*

/**
 * 自定义toast
 */
@Route(path = RoutePath.COMMON_ACTIVITY_TOAST_UTIL)
class ToastUtilActivity : BaseActivity() {

    override fun getLayoutId(): Int = R.layout.activity_toast_util

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListener()
    }

    private fun initListener() {
        success.setOnClickListener { ToastUtil.success("成功") }
        warning.setOnClickListener { ToastUtil.waring("警告") }
        error.setOnClickListener { ToastUtil.error("失败") }
        defaultShow.setOnClickListener { ToastUtil.show("默认普通的") }
        imageShow.setOnClickListener { ToastUtil.show("默认普通的", R.drawable.success); }

    }
}