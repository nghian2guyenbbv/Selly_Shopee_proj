package selly.authenticate.sellyUser;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SellyUser {
    private String userName;
    private String passWord;
}
