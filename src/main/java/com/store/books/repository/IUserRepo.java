package com.store.books.repository;

import com.store.books.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IUserRepo extends JpaRepository<User, Integer> {

    // Used custom query to get user by email.
    @Query(value = "SELECT * FROM user WHERE email = :email", nativeQuery = true)
    User findUserByEmail(String email);

    // Used custom query to check login credentials.
    @Query(value = "SELECT * FROM user WHERE email = :email and password = :password", nativeQuery = true)
    User findByLoginDetails(String email, String password);
}
