package com.store.books.service;

import com.store.books.dto.LoginDTO;
import com.store.books.dto.UserDTO;
import com.store.books.model.User;

import java.util.List;

public interface IUserService {

    // Abstract method to add new user.
    User addUser(UserDTO userDTO);

    // Abstract method to get all user.
    List<User> getAllUser();

    // Abstract method to get user by id.
    User getUser(int id);

    // Abstract method to get user by email.
    // Overloaded method.
    User getUser(String email);

    // Abstract method to update user by email.
    User updateUser(String email, UserDTO userDTO);

    // Abstract method to update user by id.
    User updateUser(int id, UserDTO userDTO);

    // Abstract method to check login details.
    User checkLogin(LoginDTO loginDTO);

    // Abstract method to change password.
    User updatePassword(LoginDTO loginDTO, String newPassword);

    // Abstract method to reset password.
    User resetPassword(String email, String newPassword, String confirmPassword);
}
