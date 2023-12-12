package ir.masoudkarimi.wanderlist.data.user

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import ir.masoudkarimi.wanderlist.data.user.api.FakeUserApi
import ir.masoudkarimi.wanderlist.data.user.api.UserApi
import ir.masoudkarimi.wanderlist.data.user.storage.DefaultUserStorage
import ir.masoudkarimi.wanderlist.data.user.storage.UserStorage

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class UserModule {

  @Binds
  abstract fun bindUserStorage(userStorage: DefaultUserStorage): UserStorage

  @Binds
  abstract fun bindUserApi(userApi: FakeUserApi): UserApi

  @Binds
  abstract fun bindUserRepository(userRepository: DefaultUserRepository): UserRepository
}