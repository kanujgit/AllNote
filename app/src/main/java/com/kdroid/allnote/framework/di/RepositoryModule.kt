package com.kdroid.allnote.framework.di

import android.app.Application
import com.kdroid.allnote.framework.RoomNoteDataSource
import com.kdroid.core.repository.NoteRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideRepo(app:Application) = NoteRepository(RoomNoteDataSource(app))

}