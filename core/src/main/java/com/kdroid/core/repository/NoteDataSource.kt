package com.kdroid.core.repository

import com.kdroid.core.data.Note

interface NoteDataSource {

    suspend fun add(note:Note)

    suspend fun get(id:Long):Note?

    suspend fun getAll():List<Note>

    suspend fun delete(note:Note)
}