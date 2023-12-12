package ir.masoudkarimi.wanderlist.data.core

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton


private const val USER_PREFERENCES = "user_preferences"

@Module
@InstallIn(SingletonComponent::class)
internal class DataStoreModule {

  @Provides
  @Singleton
  internal fun provideDataStore(
    @ApplicationContext context: Context,
    dispatchers: CoroutineDispatchers,
  ): DataStore<Preferences> {
    return PreferenceDataStoreFactory.create(
      corruptionHandler = ReplaceFileCorruptionHandler(
        produceNewData = { emptyPreferences() },
      ),
      migrations = listOf(SharedPreferencesMigration(context, USER_PREFERENCES)),
      scope = CoroutineScope(dispatchers.io + SupervisorJob()),
      produceFile = { context.preferencesDataStoreFile(USER_PREFERENCES) },
    )
  }
}