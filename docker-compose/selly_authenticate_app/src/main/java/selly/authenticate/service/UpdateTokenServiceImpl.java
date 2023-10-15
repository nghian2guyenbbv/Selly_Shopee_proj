package selly.authenticate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import selly.authenticate.dto.UserSellyDto;
import selly.authenticate.repositories.UserSellyRepo;

@Service
@Slf4j
public class UpdateTokenServiceImpl implements UpdateTokenService {

    @Autowired
    private UserSellyRepo userSellyRepo;

    @Override
    public String getTokenWithUser(String user) {
        System.out.println("user: "+user);
        UserSellyDto userSellyDto = userSellyRepo.findTokenByUserName(user);
        System.out.println("token from repo: "+userSellyDto.getToken());
        log.info("token from repo: {} for user {}"+userSellyDto.getToken(), user);
        return userSellyDto.getToken();
    }

    @Override
    public void updateTokenWithUser(String userName, String token) {
        userSellyRepo.updateTokenByUserName(userName, token);
    }
}
