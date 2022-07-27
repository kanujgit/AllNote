package com.kdroid.allnote.view.list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.kdroid.allnote.framework.RoomNoteDataSource
import com.kdroid.allnote.framework.UseCases
import com.kdroid.allnote.framework.di.ApplicationModule
import com.kdroid.allnote.framework.di.DaggerViewModelComponent
import com.kdroid.core.data.Note
import com.kdroid.core.repository.NoteRepository
import com.kdroid.core.usecase.AddNote
import com.kdroid.core.usecase.GetAllNote
import com.kdroid.core.usecase.GetNote
import com.kdroid.core.usecase.RemoveNote
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel(application: Application):AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)
    private val repository = NoteRepository(RoomNoteDataSource(application))

    @Inject
    lateinit var useCases:UseCases

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(getApplication()))
            .build()
            .inject(this)
    }

    val note = MutableLiveData<List<Note>>()

    fun getAllNoteData(){
        coroutineScope.launch{
            val noteList = useCases.getAllNote()
            note.postValue(noteList)
        }
    }


}