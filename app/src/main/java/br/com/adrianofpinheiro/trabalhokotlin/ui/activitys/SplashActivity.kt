package br.com.adrianofpinheiro.trabalhokotlin.ui.activitys

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import br.com.adrianofpinheiro.trabalhokotlin.R
import br.com.adrianofpinheiro.trabalhokotlin.ui.LoginActivity
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    private val SPLASH_SCREEN_TIMEOUT = 2000L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val preferences = getSharedPreferences("user_preferences", Context.MODE_PRIVATE)
        val isFirstOpen = preferences.getBoolean("open_first", false)

        if (isFirstOpen) {
            showLogin()
        } else {
            markAppAlreadyOpen(preferences)
            showSplash()
        }

    }

    private fun markAppAlreadyOpen(preferences: SharedPreferences) {
        val editor = preferences.edit()
        editor.putBoolean("open_first", false)
        editor.apply()
    }

    private fun showLogin() {
        val nextScreen = Intent(this@SplashActivity, LoginActivity::class.java)
        startActivity(nextScreen)
        finish()
    }

    private fun showSplash() {
        val anim = AnimationUtils.loadAnimation(this, R.anim.splash_animation)
        anim.reset()
        ivLogo.clearAnimation()
        ivLogo.startAnimation(anim)

        Handler().postDelayed({
            val nextScreen = Intent(this@SplashActivity, LoginActivity::class.java)
            startActivity(nextScreen)
            finish()
        }, SPLASH_SCREEN_TIMEOUT)
    }
}
