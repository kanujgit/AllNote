package com.kdroid.allnote.framework

import com.kdroid.core.usecase.*

data class UseCases(
    val addNote: AddNote,
    val getAllNote: GetAllNote,
    val getNote: GetNote,
    val removeNote: RemoveNote,
    val wordCount:WordCount
)