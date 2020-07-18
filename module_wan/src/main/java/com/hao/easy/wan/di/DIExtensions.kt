package com.hao.easy.wan.di

import android.app.Activity
import androidx.fragment.app.Fragment
import com.hao.easy.base.BaseApplication
import com.hao.easy.wan.di.component.ActivityComponent
import com.hao.easy.wan.di.component.DaggerActivityComponent
import com.hao.easy.wan.di.component.DaggerFragmentComponent
import com.hao.easy.wan.di.component.FragmentComponent

fun Activity.component(): ActivityComponent =
        DaggerActivityComponent.builder()
                .appComponent(BaseApplication.instance.appComponent)
                .build()

fun Fragment.component(): FragmentComponent =
        DaggerFragmentComponent.builder()
                .appComponent(BaseApplication.instance.appComponent)
                .build()
