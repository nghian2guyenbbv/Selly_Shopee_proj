package com.example.getartfromselly.service;

import com.example.getartfromselly.dto.SellyLoginDto;

public interface GetSellyTokenService {
   String getCurrentToken(SellyLoginDto sellyLoginDto);
   String refreshToken(SellyLoginDto sellyLoginDto);
}
