package com.kdroid.core.usecase

import com.kdroid.core.data.Note
import com.kdroid.core.repository.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {
    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}