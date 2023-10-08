package selly.authenticate.login.service;

import selly.authenticate.sellyUser.SellyUser;

import java.util.Optional;

public interface SellyLoginService {

    public Optional<String> getTokenWithUserAndPass(SellyUser sellyUser);
}
