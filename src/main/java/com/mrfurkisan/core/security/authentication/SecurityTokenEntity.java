package com.mrfurkisan.core.security.authentication;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

// Veritabanında tutulan nesne. Hiçbir şekilde Client tarafına gitmeycek. Oturum
// sonlandırıldığında
// Veritabanından silinecek.
@Entity
@Table(name = "Tokens") // jpa kullanılırsa diye burada dursun
@Data
public class SecurityTokenEntity  implements ISecurityTokenEntity {

    @Id
    private String unique_id;
    private String mac_address;
    private String role_id;
    private int user_id;

}
