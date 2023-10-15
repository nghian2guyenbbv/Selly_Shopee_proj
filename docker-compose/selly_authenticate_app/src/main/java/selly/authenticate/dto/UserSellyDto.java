package selly.authenticate.dto;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "USER_AUTHENTICATE")
public class UserSellyDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "TOKEN")
    private String token;
}
