package ir.masoudkarimi.wanderlist.data.model.values

@JvmInline
value class Password(val value: String) {

  init {
    require(value.length >= MIN_PASSWORD_LENGTH) {
      "Password must be at least $MIN_PASSWORD_LENGTH characters long."
    }

    require(!value.contains(" ")) {
      "Password can't contain space character"
    }
  }

  // Mask the password for security
  override fun toString() = "********"

  companion object {
    private const val MIN_PASSWORD_LENGTH = 8
  }
}