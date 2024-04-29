package com.beemer.unofficial.fromis_9.model.di

import com.beemer.unofficial.fromis_9.model.repository.AlbumRepository
import com.beemer.unofficial.fromis_9.model.service.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = RetrofitClient.getRetrofit()

    @Provides
    @Singleton
    fun provideAlbumRepository(retrofit: Retrofit): AlbumRepository = AlbumRepository(retrofit)
}