package selly.authenticate.login.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SellyTokenDto {
    private String code;
    @JsonProperty("data")
    private SellyLoginData data;
    private String message;

    @Data
    public class SellyLoginData {
        private boolean isFirstLogin;
        private boolean isNewUser;
        private String token;
    }
}

