package com.example.rootbright

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.rootbright.data.ListViewModel
import com.example.rootbright.data.MemoData

import kotlinx.android.synthetic.main.activity_list.*
import java.util.*

class ListActivity : AppCompatActivity() {

    /**
     * ListViewModel 을 담을 변수를 추가
     * */
    private var viewModel : ListViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        setSupportActionBar(toolbar)

        /**
         * MemoListFragment 를 화면에 표시
         * */
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.contentLayout,MemoListFragment())
        fragmentTransaction.commit()


        /**
         * DetailActivity 로 이동하는 코드
         * */
        fab.setOnClickListener { view ->
            //val intent = Intent(applicationContext, DetailActivity::class.java)
            //startActivity(intent)
            viewModel!!.let {
                var memoData = MemoData()
                memoData.title = "제목 테스트"
                memoData.summary = "요약내용 테스트"
                memoData.createdAt = Date()

                it.addMemo(memoData)
            }



        }

        /**
         * ListViewModel 을 가져오는 코드
         * 앱의 객체인 application 이 null 인지 먼저 체크함
         * */

        viewModel = application!!.let {
            /**
             * ViewModel 을 가져오기 위해 ViewModelProvider 객체를 생성
             * viewModelStore 는 ViewModel 의 생성과 소멸의 기준
             * ViewModelFactory 는 ViewModel 을 실제로 생성하는 객체
             * ViewModelProvider 의 get 함수를 통해 ListViewModel 을 얻을 수 있음
             * */
            ViewModelProvider(viewModelStore, ViewModelProvider.AndroidViewModelFactory(it)).get(ListViewModel::class.java)
        }



    }

}
