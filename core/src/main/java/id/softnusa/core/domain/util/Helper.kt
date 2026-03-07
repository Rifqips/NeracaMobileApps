package id.softnusa.core.domain.util

import java.text.NumberFormat
import java.util.*
fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS
        .matcher(email)
        .matches()
}

fun calculatePasswordStrength(password: String): Int {
    var score = 0

    if (password.length >= 8) score++
    if (password.any { it.isDigit() }) score++
    if (password.any { it.isUpperCase() }) score++
    if (password.any { "!@#$%^&*()_+".contains(it) }) score++

    return score
}


fun formatRupiah(number: Long): String {
    val format = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
    format.maximumFractionDigits = 0
    return format.format(number)
}