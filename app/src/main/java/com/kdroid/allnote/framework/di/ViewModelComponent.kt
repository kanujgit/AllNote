package com.kdroid.allnote.framework.di

import com.kdroid.allnote.view.list.ListViewModel
import com.kdroid.allnote.view.note.NoteViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCaseModule::class])
interface ViewModelComponent {

    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: ListViewModel)

}