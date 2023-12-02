package selly.authenticate.service;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import selly.authenticate.dto.UserSellyDto;
import selly.authenticate.repositories.UserSellyRepo;
import selly.authenticate.sellyUser.SellyUser;

import java.util.List;

@Service
public class GetUserInfoFromAuthenDBServiceImpl implements GetUserInfoFromAuthenDBService {
    @Autowired
    private UserSellyRepo userSellyRepo;

    @Override
    public SellyUser getSellyUserFromDb() {
        List<UserSellyDto> users = userSellyRepo.findAll();
        return CollectionUtils.emptyIfNull(users).stream().findFirst().map(userDto ->
                SellyUser.builder().userName(userDto.getUserName()).passWord(userDto.getPassword()).build()
        ).orElse(null);
    }
}
