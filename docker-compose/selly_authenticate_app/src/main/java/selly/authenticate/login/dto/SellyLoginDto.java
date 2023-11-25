package selly.authenticate.login.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SellyLoginDto {
    private String phone;
    private String passWord;
}
