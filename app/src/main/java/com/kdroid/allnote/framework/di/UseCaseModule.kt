package com.kdroid.allnote.framework.di

import com.kdroid.allnote.framework.UseCases
import com.kdroid.core.repository.NoteRepository
import com.kdroid.core.usecase.AddNote
import com.kdroid.core.usecase.GetAllNote
import com.kdroid.core.usecase.GetNote
import com.kdroid.core.usecase.RemoveNote
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    fun getUseCase(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetAllNote(repository),
        GetNote(repository),
        RemoveNote(repository)
    )

}