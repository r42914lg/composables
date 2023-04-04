package ru.dars.darsapp.core.ui.compose

import android.util.Patterns
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import ru.dars.darsapp.core.base.extensions.getBaseCountryCode
import ru.dars.darsapp.core.util.log
import ru.dars.darsapp.ui.auth.PhoneFragment
import java.util.IllegalFormatException

object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor() = Color.Unspecified

    @Composable
    override fun rippleAlpha(): RippleAlpha = RippleAlpha(0.0f,0.0f,0.0f,0.0f)
}

fun String.isValidPhone(): Boolean {
    if (!Patterns.PHONE.matcher(this).matches())
        return false

    val barePhone = this
        .replace(" ", "")
        .replace("-", "")
        .replace("(", "")
        .replace(")", "")
        .replace("+", "")

    return (barePhone.startsWith("7") || barePhone.startsWith("8")) && barePhone.length == 11
}

fun String.asBarePhone(): String {
    if (!this.isValidPhone())
        throw IllegalArgumentException("$this is not a valid phone number")

    val barePhone = this
        .replace(" ", "")
        .replace("-", "")
        .replace("(", "")
        .replace(")", "")
        .replace("+", "")

    return "+7${barePhone.drop(1)}"
}