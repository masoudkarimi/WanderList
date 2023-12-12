package ir.masoudkarimi.wanderlist.data.user

import app.cash.turbine.test
import ir.masoudkarimi.wanderlist.data.model.user.sampleUser
import ir.masoudkarimi.wanderlist.data.user.api.FakeUserApi
import ir.masoudkarimi.wanderlist.data.user.storage.FakeUserStorage
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class UserRepositoryTest {

  private val userApi = FakeUserApi()
  private val userStorage = FakeUserStorage()
  private val userRepository = DefaultUserRepository(userApi, userStorage)

  @Test
  fun `Logged in user is null at first`() = runTest {
    userRepository.getLoggedInUser().test {
      assertNull(awaitItem())
    }
  }

  @Test
  fun `Register new user`() = runTest {
    userRepository.getLoggedInUser().test {
      userRepository.registerUser(
        name = sampleUser.name?.value,
        username = sampleUser.username.value,
        password = sampleUser.password.value,
      )

      assertNull(awaitItem())
      val user = awaitItem()
      assertNotNull(user)
      assertEquals(sampleUser.name, user!!.name)
      assertEquals(sampleUser.username, user.username)
    }
  }

  @Test
  fun `User is cleared after log out`() = runTest {
    userRepository.getLoggedInUser().test {
      userRepository.registerUser(
        name = sampleUser.name?.value,
        username = sampleUser.username.value,
        password = sampleUser.password.value,
      )

      userRepository.clearLoggedInUser()

      assertNull(awaitItem())
      val user = awaitItem()
      assertNotNull(user)
      assertNull(awaitItem())
    }
  }
}