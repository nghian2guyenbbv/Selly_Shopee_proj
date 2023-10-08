package selly.authenticate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import selly.authenticate.dto.UserSellyDto;
import selly.authenticate.repositories.UserSellyRepo;

@Service
public class UpdateTokenServiceImpl implements UpdateTokenService {

    @Autowired
    private UserSellyRepo userSellyRepo;

    @Override
    public String getTokenWithUser(String user) {
        UserSellyDto userSellyDto = userSellyRepo.findTokenByUserName(user);
        System.out.println("token from repo: "+userSellyDto.getToken());
        return userSellyDto.getToken();
    }

    @Override
    public void updateTokenWithUser(String userName, String token) {
        userSellyRepo.updateTokenByUserName(userName, token);
    }
}
