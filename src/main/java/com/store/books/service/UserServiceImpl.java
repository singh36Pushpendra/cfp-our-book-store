package com.store.books.service;

import com.store.books.dto.LoginDTO;
import com.store.books.dto.UserDTO;
import com.store.books.exception.BookStoreException;
import com.store.books.model.User;
import com.store.books.repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// To mark a class as a service provider.
@Service
public class UserServiceImpl implements IUserService {

    @Autowired // Automatic Dependency Injection.
    IUserRepo userRepo;

    @Override
    // Adding a new user to database.
    public User addUser(UserDTO userDTO) {
        User user = new User(userDTO);
        return userRepo.save(user);
    }

    @Override
    // Getting all user from database.
    public List<User> getAllUser() {
        List<User> users = userRepo.findAll();
        return users;
    }

    @Override
    // Getting user by id.
    public User getUser(int id) {
        Optional<User> optionalUser = userRepo.findById(id);
        if (optionalUser.isPresent())
            return optionalUser.get();

        throw new BookStoreException("Invalid: User id: '" + id + "' does'nt exists in repo!");
    }

    @Override
    // Getting user by email.
    public User getUser(String email) {
        User user = userRepo.findUserByEmail(email);
        if (user == null)
            throw new BookStoreException("Invalid: User email: '" + email + "' does'nt exists! in repo!");
        return user;
    }

    @Override
    // Updating user by email.
    public User updateUser(String email, UserDTO userDTO) {
        User userByEmail = userRepo.findUserByEmail(email);
        if (userByEmail == null)
            throw new BookStoreException("Can't Update: User email: '" + email + "' does'nt exists! in repo!");
        User user = new User(userDTO);
        user.setId(userByEmail.getId());
        return userRepo.save(user);
    }

    @Override
    // Updating user by id.
    // Overloaded method.
    public User updateUser(int id, UserDTO userDTO) {
        Optional<User> optionalUser = userRepo.findById(id);

        if (optionalUser.isPresent()) {
            User user = new User(userDTO);
            user.setId(id);
            return userRepo.save(user);
        }
        throw new BookStoreException("Can't Update: User id is not present in repo!");
    }

    @Override
    // Checking login details.
    public User checkLogin(LoginDTO loginDTO) {
        User user = userRepo.findByLoginDetails(loginDTO.email, loginDTO.password);
        if (user == null)
            throw new BookStoreException("Incorrect user or password login!");
        return user;
    }

    // Changing user password.
    @Override
    public User updatePassword(LoginDTO loginDTO, String newPassword) {
        User user = checkLogin(loginDTO);
        user.setPassword(newPassword);
        return userRepo.save(user);
    }

    @Override
    // Reseting user password.
    public User resetPassword(String email, String newPassword, String confirmPassword) {
        User user = getUser(email);
        if (user == null)
            throw new BookStoreException("User email = '" + user.getEmail() + "' does'nt exists in repository!");
        if (newPassword.equals(confirmPassword)) {
            user.setPassword(confirmPassword);
            return userRepo.save(user);
        }
        throw new BookStoreException("New Password & Confirm Password does'nt matches!");
    }

}