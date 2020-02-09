package com.example.rootbright.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rootbright.R
import kotlinx.android.synthetic.main.item_memo.view.*
import java.text.SimpleDateFormat

class MemoListAdapter (private val list: MutableList<MemoData>) : RecyclerView.Adapter<ItemViewHolder>(){

    /**
     * Date 객체를 사람이 볼수 있는 문자열로 변환하기 위한 객체
     * */
    private val dateFormat = SimpleDateFormat("MM/dd HH:mm")

    /**
     * item_memo 를 불러 ViewHolder 를 생성함
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_memo,parent,false)
        return ItemViewHolder(view)
    }

    /**
     * list 내의 MemoData 의 개수를 반환함
     * */
    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        /**
         * 제목(title)을 titleView에 표시
         * 제목이 있는 경우 titleView 를 화면에 표시(VISIBLE) 하고
         * title 값을 할당하여 보여 줌
         * VISIBLE : View 를 화면에 표시
         * INVISIBLE : View 의 내용만 감추고 영역은 유지
         * */
        if (list[position].title.isEmpty()){
            holder.containerView.titleView.visibility = View.VISIBLE
            holder.containerView.titleView.text = list[position].title
        }
        else{
            /**
             * 제목이 없는 경우 titleView 의 영역까지 숨겨줌 (GONE)
             * GONE : View 의 내용 및 영역까지 제거
             * */
            holder.containerView.titleView.visibility = View.GONE
        }
        /**
         * 요약내용(summary) 을 summaryView 에 표시함
         * */
        holder.containerView.summaryView.text= list[position].summary
        /**
         * 작성시간(createAt) 을 dateFormat 으로 변환하여 dateView 에 표시함
         * */
        holder.containerView.dateView.text = dateFormat.format(list[position].createdAt)
    }

}