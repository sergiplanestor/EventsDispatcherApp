package com.revolhope.data.injection

import com.revolhope.data.feature.event.repositoryimpl.EventRepositoryImpl
import com.revolhope.domain.feature.event.repository.EventRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindEventRepository(
        eventRepositoryImpl: EventRepositoryImpl
    ): EventRepository

}
