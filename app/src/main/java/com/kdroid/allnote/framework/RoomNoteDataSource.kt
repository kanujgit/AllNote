package com.kdroid.allnote.framework

import android.content.Context
import com.kdroid.allnote.framework.db.DatabaseService
import com.kdroid.allnote.framework.db.NoteEntity
import com.kdroid.core.data.Note
import com.kdroid.core.repository.NoteDataSource

class RoomNoteDataSource(context: Context) : NoteDataSource {

    val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) = noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long) = noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll() = noteDao.getAllNoteEntity().map { it.toNote() }

    override suspend fun delete(note: Note) = noteDao.removeNoteEntity(NoteEntity.fromNote(note))

}