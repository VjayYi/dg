package com.dana.gaib.ga.danagaib.page

import android.os.Bundle
import com.dana.gaib.ga.danagaib.R
import com.dana.gaib.ga.danagaib.base.BaseActivity
import com.dana.gaib.ga.danagaib.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(){

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
