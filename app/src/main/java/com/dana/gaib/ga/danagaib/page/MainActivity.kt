package com.dana.gaib.ga.danagaib.page

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.dana.gaib.ga.danagaib.R
import com.dana.gaib.ga.danagaib.base.BaseActivity
import com.dana.gaib.ga.danagaib.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    companion object {
        /**
         * 界面入口
         *
         * @param context Context 对象
         */
        fun actionStart(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun initTitleBar() {
        super.initTitleBar()
        showTitleBar()
        setTitleStr("首页")
    }
}
