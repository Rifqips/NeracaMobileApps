package id.softnusa.core.domain.model.response.auth

data class ResponseLogin(
    val accessToken: String,
    val refreshToken: String
)
