package com.luis.luis_books.repository;

import com.luis.luis_books.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserInfo, Integer> {

    UserInfo findByUsername(String username);
}
