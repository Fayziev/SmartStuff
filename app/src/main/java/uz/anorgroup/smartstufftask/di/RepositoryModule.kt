package uz.anorgroup.smartstufftask.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.anorgroup.smartstufftask.domain.AppRepository
import uz.anorgroup.smartstufftask.domain.impl.AppRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun getAppRepository(): AppRepository = AppRepositoryImpl()
}