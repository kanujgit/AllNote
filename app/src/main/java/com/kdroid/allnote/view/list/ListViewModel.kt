package com.kdroid.allnote.view.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.kdroid.allnote.framework.RoomNoteDataSource
import com.kdroid.allnote.framework.UseCases
import com.kdroid.core.data.Note
import com.kdroid.core.repository.NoteRepository
import com.kdroid.core.usecase.AddNote
import com.kdroid.core.usecase.GetAllNote
import com.kdroid.core.usecase.GetNote
import com.kdroid.core.usecase.RemoveNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(application: Application):AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val repository = NoteRepository(RoomNoteDataSource(application))

    private val useCases = UseCases(
        AddNote(repository),
        GetAllNote(repository),
        GetNote(repository),
        RemoveNote(repository)
    )

    val note = MutableLiveData<List<Note>>()

    fun getAllNoteData(){
        coroutineScope.launch{
            val noteList = useCases.getAllNote()
            note.postValue(noteList)
        }
    }


}