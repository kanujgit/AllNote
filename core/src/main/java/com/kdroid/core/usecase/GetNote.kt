package com.kdroid.core.usecase

import com.kdroid.core.repository.NoteRepository

class GetNote(private val repository: NoteRepository) {
    suspend operator fun invoke(id: Long) = repository.getNote(id)

}