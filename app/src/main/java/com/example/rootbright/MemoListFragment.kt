package com.example.rootbright


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rootbright.data.ListViewModel
import com.example.rootbright.data.MemoListAdapter
import kotlinx.android.synthetic.main.fragment_memo_list.*

/**
 * A simple [Fragment] subclass.
 */
class MemoListFragment : Fragment() {
    /**
     * MemoListAdapter 와 ListViewModel 을 담을 속성을 선언
     * */
    private lateinit var listAdapter:MemoListAdapter
    private var viewModel: ListViewModel? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_memo_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        /**
         * activity 속성은 ListActivity 를 가리키는 것
         * activity 의 viewModelStore 를 쓰는 이유는
         * activity 와 viewModel 을 공유할 수 있기 때문
         * fragment의 viewModelStore 를 사용한다면
         * MemoListFragment 만의 viewModel 이 따로 생성됨
         * */
        viewModel = activity!!.application!!.let {
            ViewModelProvider(
                activity!!.viewModelStore,
                ViewModelProvider.AndroidViewModelFactory(it)).get(ListViewModel::class.java)
        }

        /**
         * MemoLiveData 를 가져와서 Adapter 에 담아 RecyclerView 에 출력하도록 함
         * */
        viewModel!!.let {
            it.memoLiveData.value?.let {
                listAdapter = MemoListAdapter(it)
                memoListView.layoutManager = LinearLayoutManager(activity,RecyclerView.VERTICAL,false)
                memoListView.adapter = listAdapter
            }
            /**
             * MemoLiveData 에 observe 함수를 통해 값이 변할 때 동작할 observer 를 붙여줌
             * (observer 내에서는 adapter 의 갱신 코드를 호출)
             * */
            it.memoLiveData.observe(this,
                Observer {
                    listAdapter.notifyDataSetChanged()
                })
        }
    }


}
