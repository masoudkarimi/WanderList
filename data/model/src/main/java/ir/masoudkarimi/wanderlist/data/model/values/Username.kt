package ir.masoudkarimi.wanderlist.data.model.values

@JvmInline
value class Username(val value: String) {

  init {
    require(value.length >= MIN_USERNAME_LENGTH) {
      "Username must be at least $MIN_USERNAME_LENGTH characters."
    }

    require(value.all(ACCEPTABLE_USERNAME_CHARS::contains)) {
      "Username should contains only numbers, characters and underscore"
    }
  }

  companion object {
    private const val MIN_USERNAME_LENGTH = 6
    const val ACCEPTABLE_USERNAME_CHARS = "1234567890_abcdefghigklmnopqrstuvwxyzABCDEFGHIGKLMNOPQRSTUVWXYZ"
  }
}