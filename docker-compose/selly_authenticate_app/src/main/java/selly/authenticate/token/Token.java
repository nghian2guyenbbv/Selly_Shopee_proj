package selly.authenticate.token;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Token {
  @JsonProperty("user")
  private String user;
  @JsonProperty("token")
  private String token;
}
