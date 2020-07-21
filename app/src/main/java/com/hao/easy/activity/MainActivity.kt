package com.hao.easy.activity

import android.os.Build
import android.view.View
import android.view.WindowManager
import androidx.drawerlayout.widget.DrawerLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.hao.easy.R
import com.hao.easy.base.adapter.FragmentAdapter
import com.hao.easy.base.ui.BaseActivity
import com.hao.easy.user.ui.fragment.UserFragment
import com.hao.easy.wan.ui.fragment.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.app_activity_main.*
import kotlin.properties.Delegates

@Route(path = "/app/MainActivity")
@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private var drawerOpened: Boolean = false
    private var backPressedTime by Delegates.observable(0L) { _, old, new ->
        if (new - old < 2000) {
            finish()
        } else {
            toast("再按返回键退出")
        }
    }

    override fun transparentStatusBar() = true
    override fun getLayoutId() = R.layout.app_activity_main
    override fun initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val lp = window.attributes
            // 使用刘海区域
            lp.layoutInDisplayCutoutMode =
                WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
            window.attributes = lp
        }

        val fragments = listOf(
            WechatFragment(),
            ProjectFragment(),
            KnowledgeFragment(),
            SearchFragment()
        )
        viewPager.apply {
            isUserInputEnabled = false
            offscreenPageLimit = 3
            adapter = FragmentAdapter(supportFragmentManager, lifecycle, fragments)
        }
        initDrawerLayout()
        initLeftNavigation()
        initBottomNavigation()
    }

    private fun initDrawerLayout() {
        drawerLayout.addDrawerListener(object : DrawerLayout.SimpleDrawerListener() {

            override fun onDrawerClosed(p0: View) {
                drawerOpened = false
            }

            override fun onDrawerOpened(p0: View) {
                drawerOpened = true
            }
        })
    }

    private fun initLeftNavigation() {
        supportFragmentManager.beginTransaction()
            .add(R.id.leftNavigationView, UserFragment())
            .commit()
    }

    private fun initBottomNavigation() {
//        bottomNavigationView.itemIconTintList = null
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            viewPager.setCurrentItem(
                when (item.itemId) {
                    R.id.tab_hot -> 0
                    R.id.tab_project -> 1
                    R.id.tab_knowledge -> 2
                    else -> 3
                }, false
            )

            true
        }
    }

    override fun onBackPressed() {
        if (drawerOpened) {
            drawerLayout.closeDrawers()
        } else {
            backPressedTime = System.currentTimeMillis()
        }
    }
}
