package com.mrfurkisan.core.security.authentication;

import com.mrfurkisan.core.domain.interfaces.IEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Users")
@Data // Lombok - @Getter final olmayanlar için @Setter ve @NoArgsConstructor ... kısaltması
public class User implements IEntity{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String email;
    private String default_mac_address;
    private String role_id;
    private String username;
    private String password;
}
