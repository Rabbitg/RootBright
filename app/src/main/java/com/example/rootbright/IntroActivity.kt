package com.example.rootbright

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View

class IntroActivity : AppCompatActivity() {

    /**
     * Handler: Runnable 을 실행하는 클래스
     * Runnable : 병렬 실행이 가능한 Thread 를 만들어주는 클래스
     * */
    var handler: Handler? = null
    var runnable: Runnable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        /**
         * 안드로이드 앱을 띄우는 Window 의 속성을 변경하여
         * 시스템 UI를 숨기고 전체화면으로 표시하는 코드
         * */
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LOW_PROFILE or
                View.SYSTEM_UI_FLAG_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION

    }

    /**
     * Runnable 이 실행되면 ListActivity 로 이동하는 코드
     * */
    override fun onResume() {
        super.onResume()

        runnable = Runnable {
            val intent = Intent(applicationContext, ListActivity::class.java)
            startActivity(intent)
        }
        /**
         * Handler 를 생성하고 2000ms(2초)후 runnable 을 실행
         * */
        handler = Handler()
        handler?.run {
            postDelayed(runnable, 2000)
        }
    }

    /**
     * Activity Pause 상태일 때는 runnable 도 중단하도록 함
     * */
    override fun onPause() {
        super.onPause()
        handler?.removeCallbacks(runnable)
    }
}
