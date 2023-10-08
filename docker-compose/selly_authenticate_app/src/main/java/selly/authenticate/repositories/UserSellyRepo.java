package selly.authenticate.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import selly.authenticate.dto.UserSellyDto;

@Repository
public interface UserSellyRepo extends JpaRepository<UserSellyDto, Long> {
    public UserSellyDto findUserSellyDtoByUserName(String userName);

    @Modifying
    @Transactional
    @Query("UPDATE UserSellyDto set token = ?2 where userName = ?1")
    int updateTokenByUserName(String userName, String token);

    public UserSellyDto findTokenByUserName(String userName);
}
