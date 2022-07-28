package com.kdroid.core.data


data class Note(
    var title: String,
    var content: String,
    var updatedTime: Long,
    var creationTime: Long,
    var id: Long = 0,
    var wordCount:Int = 0
)