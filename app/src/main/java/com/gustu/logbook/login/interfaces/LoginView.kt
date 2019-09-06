package com.gustu.logbook.login.interfaces

import com.gustu.logbook.login.model.LoginResponse

interface LoginView {
    fun _onLogin(loginResponse: LoginResponse)
    fun _onFailed()
}
