package com.kdroid.core.usecase

import com.kdroid.core.data.Note
import com.kdroid.core.repository.NoteRepository

class RemoveNote(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note) = repository.removeNote(note)

}