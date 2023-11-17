package ir.masoudkarimi.wanderlist.data.core

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class CoreModule {

  @Singleton
  @Provides
  fun provideCoroutineDispatchers(): CoroutineDispatchers = DefaultCoroutineDispatchers(
    io = Dispatchers.IO,
    main = Dispatchers.Main,
    default = Dispatchers.Default,
  )
}