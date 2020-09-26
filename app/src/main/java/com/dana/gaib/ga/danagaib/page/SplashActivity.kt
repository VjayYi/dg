package com.dana.gaib.ga.danagaib.page

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.dana.gaib.ga.danagaib.R
import com.dana.gaib.ga.danagaib.constants.SPLASH_TIME_DELAY


/**
 * Created by YiVjay
 * on 2020/9/26
 */
class SplashActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            MainActivity.actionStart(this)
            finish()
        }, SPLASH_TIME_DELAY)
    }


}