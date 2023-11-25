package selly.authenticate.login.service;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import selly.authenticate.login.dto.SellyLoginDto;
import selly.authenticate.login.dto.SellyTokenDto;
import selly.authenticate.login.service.exception.SellyLoginException;
import selly.authenticate.repositories.UserSellyRepo;
import selly.authenticate.sellyUser.SellyUser;

import java.util.Optional;

@Service
public class SellyLoginServiceImpl implements SellyLoginService {
    @Value("${selly.login.url}")
    public String SELLY_LOGIN_URL;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserSellyRepo sellyRepo;

    private HttpHeaders getDefaultHeader(){
        // Default header
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return httpHeaders;
    }

    @Override
    public Optional<String> getTokenWithUserAndPass(SellyUser sellyUser) {
        SellyLoginDto body = SellyLoginDto.builder().phone(sellyUser.getUserName()).passWord(sellyUser.getPassWord()).build();
        HttpEntity loginEntity = new HttpEntity(body, getDefaultHeader());
        System.out.println("selly login loginEntity: "+loginEntity.toString());
        String token = Strings.EMPTY;
        try{
            ResponseEntity<SellyTokenDto> responseEntity = restTemplate.exchange(SELLY_LOGIN_URL, HttpMethod.POST, loginEntity, SellyTokenDto.class);
            System.out.println("call to selly login");
            token = responseEntity.getBody().getData().getToken();
        }catch(HttpClientErrorException ex){
            throw new SellyLoginException(ex.getMessage());
        }

        return Optional.ofNullable(token);
    }

    @Override
    public Optional<String> getCurrentToken(SellyUser sellyUser) {
        return Optional.ofNullable(sellyRepo.findTokenByUserName(sellyUser.getUserName()).getToken());
    }
}
