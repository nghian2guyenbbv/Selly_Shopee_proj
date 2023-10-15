package selly.authenticate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import selly.authenticate.login.service.SellyLoginService;
import selly.authenticate.sellyUser.SellyUser;
import selly.authenticate.service.UpdateTokenService;
import selly.authenticate.token.Token;

import java.util.Optional;

@RestController
@RequestMapping("/sellyAuthenticate")
@Slf4j
public class SellyAuthenticateController {
    private static final String NO_TOKEN = "NO_TOKEN";

    @Autowired
    private SellyLoginService sellyLoginService;

    @Autowired
    private UpdateTokenService updateTokenService;

    @PostMapping("/refreshToken")
    public Token getSellyToken(@RequestBody SellyUser sellyUser) {
        String tokenWithUser = updateTokenService.getTokenWithUser(sellyUser.getUserName());

        Optional<String> sellyToken = sellyLoginService.getTokenWithUserAndPass(sellyUser);
        sellyToken.ifPresent(sellyTk->{
            if(!sellyTk.equals(tokenWithUser)){updateTokenService.updateTokenWithUser(sellyUser.getUserName(), sellyTk);}
        });
        return Token.builder().user(sellyUser.getUserName()).token(sellyToken.get()).build();
    }

    @PostMapping("/getCurrentToken")
    public Token getTokenFromDb(@RequestBody SellyUser sellyUser){
        Optional<String> currentToken = sellyLoginService.getCurrentToken(sellyUser);
        return Token.builder().user(sellyUser.getUserName()).token(currentToken.orElse(NO_TOKEN)).build();
    }
}

