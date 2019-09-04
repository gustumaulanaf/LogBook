package com.gustu.logbook.login.interfaces;

import com.gustu.logbook.login.model.LoginResponse;

public interface LoginView {
    void _onLogin (LoginResponse loginResponse);
    void _onFailed();
}
