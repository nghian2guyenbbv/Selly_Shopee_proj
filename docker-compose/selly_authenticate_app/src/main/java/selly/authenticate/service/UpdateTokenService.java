package selly.authenticate.service;

public interface UpdateTokenService {
  public String getTokenWithUser(String user);
  public void updateTokenWithUser(String userName, String token);

}
