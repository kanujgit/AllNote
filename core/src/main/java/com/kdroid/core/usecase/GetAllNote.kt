package com.kdroid.core.usecase

import com.kdroid.core.data.Note
import com.kdroid.core.repository.NoteRepository

class GetAllNote(private val repository: NoteRepository) {
    suspend operator fun invoke() = repository.getAllNote()

}