package com.luis.luis_books.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserInfo {

    @Id
    private Integer idUser;
    @Column(nullable = false, unique = true, length = 50)
    private String username;
    @Column(nullable = false, length = 60)
    private String password;
    @Column(nullable = false, length = 50)
    private String role;

}
