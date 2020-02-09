package com.example.rootbright.data

import java.util.*

/**
 * !! - 메모의 내용을 담을 클래스 - !!
 * 다음 시간에 배울 Realm 데이터베이스
 * 라이브러리에서 재활용 할 수 있음
 * */
/**
 *
 * */
class MemoData(
    var id: String = UUID.randomUUID().toString(), // 메모의 고유 ID
    var createdAt: Date = Date(), // 작성시간
    var title: String = "", // 제목
    var content: String="", // 내용
    var summary: String = "", // 요약
    var imageFile:String = "", // 첨부이미지 파일이름
    var latitude: Double = 0.0, // 위도
    var longitude:Double = 0.0, // 경도
    var alarmTime: Date = Date(), // 알람시간
    var weather : String = "" // 날씨
)