package ir.masoudkarimi.wanderlist.data.model.values

import org.junit.Assert.*
import org.junit.Test

class PasswordTest {

  @Test
  fun `Password must be at least 8 characters`() {
    assertThrows(IllegalArgumentException::class.java) {
      Password("test123")
    }
  }

  @Test
  fun `Password can be 8 characters`() {
    Password("test1234")
  }

  @Test
  fun `Password can't contain space character`() {
    assertThrows(IllegalArgumentException::class.java) {
      Password("test 1234")
    }
  }

  @Test
  fun `Password characters should not be printable using toString`() {
    val password = Password("test1234")
    val value = "$password"
    assertTrue(value != password.value)
    assertTrue(value.all { it == '*' })
  }
}