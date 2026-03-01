package id.softnusa.core.domain.util

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