package com.hao.easy.ui.activity

import android.content.Context
import android.content.Intent
import com.hao.easy.base.ui.BaseActivity
import com.hao.easy.R
import com.hao.easy.ui.fragment.LoginFragment
import com.hao.easy.ui.fragment.RegisterFragment

class LoginActivity : BaseActivity() {

    companion object{
        fun start(context: Context){
            context.startActivity(Intent(context,LoginActivity::class.java))
        }
    }

    private val loginFragment: LoginFragment by lazy { LoginFragment() }

    private val registerFragment: RegisterFragment by lazy { RegisterFragment() }

    override fun showToolbar() = false

    override fun getLayoutId() = R.layout.user_activity_login

    override fun initView() {
        supportFragmentManager
                .beginTransaction().apply {
                    add(R.id.frame, loginFragment, "Login")
                    commit()
                }
    }

    fun goRegister() {
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(
                    R.anim.user_fragment_right_in,
                    R.anim.user_fragment_left_out,
                    R.anim.user_fragment_left_in,
                    R.anim.user_fragment_right_out
            )
            hide(loginFragment)
            add(R.id.frame, registerFragment, "Register")
            addToBackStack(null)
            commit()
        }
    }
}
