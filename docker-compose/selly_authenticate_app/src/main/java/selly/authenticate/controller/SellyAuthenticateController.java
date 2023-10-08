package selly.authenticate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import selly.authenticate.login.service.SellyLoginService;
import selly.authenticate.sellyUser.SellyUser;
import selly.authenticate.service.UpdateTokenService;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/sellyAuthenticate")
public class SellyAuthenticateController {

    @Autowired
    private SellyLoginService sellyLoginService;

    @Autowired
    private UpdateTokenService updateTokenService;

    @PostMapping("/getToken")
    public String getSellyToken(@RequestBody SellyUser sellyUser) {
        System.out.println("get token for user: " + sellyUser.getUserName());
        String tokenWithUser = updateTokenService.getTokenWithUser(sellyUser.getUserName());

        Optional<String> sellyToken = sellyLoginService.getTokenWithUserAndPass(sellyUser);
        sellyToken.ifPresent(sellyTk->{
            if(!sellyTk.equals(tokenWithUser)){updateTokenService.updateTokenWithUser(sellyUser.getUserName(), sellyTk);}
        });
        System.out.println("token is: " + sellyToken);
        return sellyToken.get();
    }

}

