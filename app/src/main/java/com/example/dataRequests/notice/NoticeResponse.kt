package com.example.dataRequests.notice

data class NoticeResponse (
    val code:  String,
    val msg:   String,
    val error: String,
    val data: NoticeBean
)