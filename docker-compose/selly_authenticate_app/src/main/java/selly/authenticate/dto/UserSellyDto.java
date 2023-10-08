package selly.authenticate.dto;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name="USER_AUTHENTICATE")
public class UserSellyDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password;
    private String token;
}
