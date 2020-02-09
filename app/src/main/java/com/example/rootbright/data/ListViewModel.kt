package com.example.rootbright.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class ListViewModel : ViewModel(){
    /**
     * MemoData 의 MutableList 를 저장하는 속성
     * */
    private val memos: MutableList<MemoData> = mutableListOf()

    /**
     * MutableList 를 담을 MutableLiveData를 추가
     * (성능을 위해서 lazy 를 사용하여 지연 초기화)
     * */
    val memoLiveData : MutableLiveData<MutableList<MemoData>> by lazy {
        MutableLiveData<MutableList<MemoData>>().apply {
            // value 값이 할당 되어야 Observer 가 작동하기 때문에
            // 먼저 기존의 value 즉 MutableList 를 가져와서
            // MemoData 를 추가한 후에 다시 value 에 넣어줘야만 합니다.
            value = memos
        }
    }

    /**
     * 메모(MemoData 객체)를 리스트에 추가하고
     * MutableLiveData 의 value 를 갱신하여
     * Observer 를 호출하도록 하는 함수
     * */
    fun addMemo(data: MemoData) {
        val tempList = memoLiveData.value
        tempList?.add(data)
        memoLiveData.value = tempList
    }

}