package com.kdroid.core.repository

import com.kdroid.core.data.Note

class NoteRepository(private val dataSource: NoteDataSource) {

    suspend fun addNote(note:Note) = dataSource.add(note)

    suspend fun getNote(id:Long) = dataSource.get(id = id)

    suspend fun getAllNote() = dataSource.getAll()

    suspend fun removeNote(note:Note) = dataSource.delete(note)

}