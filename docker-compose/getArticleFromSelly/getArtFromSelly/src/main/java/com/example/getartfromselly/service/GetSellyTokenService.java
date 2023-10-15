package com.example.getartfromselly.service;

import com.example.getartfromselly.login.token.Token;

public interface GetSellyTokenService {
   Token getCurrentToken();
   Token refreshToken();
}
