package ir.masoudkarimi.wanderlist.data.model.values

import org.junit.Assert.*
import org.junit.Test

class UsernameTest {

  @Test
  fun `Username can't be empty`() {
    assertThrows(IllegalArgumentException::class.java) {
      Username("")
    }
  }

  @Test
  fun `Username should be at least 6 characters`() {
    assertThrows(IllegalArgumentException::class.java) {
      Username("user1")
    }
  }

  @Test
  fun `Username should only contains numbers and characters and underscores`() {
    assertThrows(IllegalArgumentException::class.java) {
      Username("user1&%^")
    }
  }

  @Test
  fun `Username can contains numbers and characters and underscores`() {
    Username("user1234")
  }
}