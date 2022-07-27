package com.kdroid.allnote.framework

import com.kdroid.core.usecase.AddNote
import com.kdroid.core.usecase.GetAllNote
import com.kdroid.core.usecase.GetNote
import com.kdroid.core.usecase.RemoveNote

data class UseCases(
    val addNote: AddNote,
    val getAllNote: GetAllNote,
    val getNote: GetNote,
    val removeNote: RemoveNote
)