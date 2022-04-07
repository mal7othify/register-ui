package com.example.loginui

 import android.text.TextUtils
import android.util.Patterns.EMAIL_ADDRESS

object Validators {
  fun String.isEmailValid(): Boolean {
    return !TextUtils.isEmpty(this) && EMAIL_ADDRESS.matcher(this).matches()
  }

  fun String.isPasswordMatching(pass: String): Boolean {
    return this == pass
  }

  private val PASSWORD_REGEX =
    """^(?=.*\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[^\w\d\s:])([^\s]){8,16}${'$'}""".toRegex()

  fun String.isPassValid(): Boolean {
    return PASSWORD_REGEX.matches(this)
  }

  private val ENGLISH_NAME_REGEX =
    """^[a-z ,.'-]+${'$'}""".toRegex()

  fun String.isValidEnglishName(): Boolean {
    return ENGLISH_NAME_REGEX.matches(this)
  }

  private val ARABIC_NAME_REGEX =
    """^[ء-ي ,.'-]+${'$'}""".toRegex()

  fun String.isValidArabicName(): Boolean {
    return ARABIC_NAME_REGEX.matches(this)
  }
}

