package cn.ysf.ratingbar

import android.graphics.Color
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import cn.ysf.common.arouter.RoutePath
import cn.ysf.common.base.BaseActivity
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.bottomnavigation.LabelVisibilityMode
import kotlinx.android.synthetic.main.activity_rb.*

@Route(path = RoutePath.RB_ACTIVITY_RATING_BAR)
class RBActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nav_view.labelVisibilityMode = LabelVisibilityMode.LABEL_VISIBILITY_LABELED
        val badge = nav_view.getOrCreateBadge(R.id.navigation_spacing)
        badge.number = 1
        badge.backgroundColor = Color.RED

        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
                setOf(
                        R.id.navigation_home,
                        R.id.navigation_spacing,
                        R.id.navigation_dashboard,
                        R.id.navigation_notifications
                )
        )
        //setupActionBarWithNavController(navController, appBarConfiguration)
        nav_view.setupWithNavController(navController)
    }

    override fun getLayoutId(): Int = R.layout.activity_rb

}