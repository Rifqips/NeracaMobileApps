package id.softnusa.core.data.mapper.auth

import id.softnusa.core.data.remote.model.request.prelogin.RequestLoginDto
import id.softnusa.core.data.remote.model.request.prelogin.RequestTokentDto
import id.softnusa.core.data.remote.model.response.prelogin.ResponseLoginDto
import id.softnusa.core.domain.model.request.auth.RequestLogin
import id.softnusa.core.domain.model.request.auth.RequestToken
import id.softnusa.core.domain.model.response.auth.ResponseLogin


fun RequestLogin.toDto(): RequestLoginDto {
    return RequestLoginDto(
        username = username,
        password = password
    )
}

fun ResponseLoginDto.toDomain(): ResponseLogin {
    return ResponseLogin(
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}

fun RequestToken.toDto(): RequestTokentDto{
    return RequestTokentDto(
        refreshToken = refreshToken
    )
}