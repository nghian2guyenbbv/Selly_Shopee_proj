package selly.authenticate.login.service;

import selly.authenticate.sellyUser.SellyUser;

import java.util.Optional;

public interface SellyLoginService {

    Optional<String> getTokenWithUserAndPass(SellyUser sellyUser);

    Optional<String> getCurrentToken(SellyUser sellyUser);
}
