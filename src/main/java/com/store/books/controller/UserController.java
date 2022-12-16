package com.store.books.controller;

import com.store.books.dto.LoginDTO;
import com.store.books.dto.ResponseDTO;
import com.store.books.dto.UserDTO;
import com.store.books.model.Email;
import com.store.books.model.User;
import com.store.books.service.IEmailService;
import com.store.books.service.IUserService;
import com.store.books.util.UserToken;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Used to create restful webservices.
@RestController
// Used to map web requests onto this handler class.
@RequestMapping("/ourbookstore/user")
public class UserController {

    // Used for automatic dependency injection.
    @Autowired
    IUserService service;

    @Autowired
    UserToken userToken;

    @Autowired
    IEmailService emailService;

    // API to register new user account.
    // Used to map HTTP post request to this handler method.
    @PostMapping("/post")
    public ResponseEntity<ResponseDTO> postUser(@Valid @RequestBody UserDTO userDTO) {
        User user = service.addUser(userDTO);
        ResponseDTO responseDTO = new ResponseDTO("You are Registered Successfully!", user, userToken.createToken(user.getId()));

        Email email = new Email(user.getEmail(), "Created New Account!", "Congratulations " + user.getFirstName() + "" +
                ", You are registered successfully on OurBookStore!");

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to get all existing user account.
    @GetMapping("/getall")
    public ResponseEntity<ResponseDTO> getAllUser() {
        ResponseDTO responseDTO = new ResponseDTO("Profile of All OurBookStore App user!", service.getAllUser(), null);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to get user by its id.
    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getById(@RequestParam int id) {
        ResponseDTO responseDTO = new ResponseDTO("Your Profile: ", service.getUser(id), null);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to get user by its email.
    @GetMapping("/getbyemail")
    public ResponseEntity<ResponseDTO> getByEmail(@RequestParam String email) {
        ResponseDTO responseDTO = new ResponseDTO("Your Profile: ", service.getUser(email), null);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to get user by token.
    @GetMapping("/getbytoken")
    public ResponseEntity<ResponseDTO> getByToken(@RequestParam String token) {
        return getById(userToken.decodeToken(token));
    }

    // API to update existing user by its email.
    @PutMapping("/putbyemail")
    public ResponseEntity<ResponseDTO> putByEmail(@RequestParam String email, @Valid @RequestBody UserDTO userDTO) {
        User user = service.updateUser(email, userDTO);

        Email emailObject = new Email(user.getEmail(), "Updated Account!", "Hello " + user.getFirstName() + "" +
                ", Your 'OurBookStore' account updated successfully!");

        emailService.sendMail(emailObject);

        ResponseDTO responseDTO = new ResponseDTO("Your profile has been Updated Successfully!", user, null);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to update existing user by generated token.
    @PutMapping("/putbytoken/{token}")
    public ResponseEntity<ResponseDTO> putByToken(@PathVariable String token, @Valid @RequestBody UserDTO userDTO) {
        int id = userToken.decodeToken(token);
        User user = service.updateUser(id, userDTO);

        Email email = new Email(user.getEmail(), "Updated Account!", "Hello " + user.getFirstName() + "" +
                ", Your 'OurBookStore' account updated successfully!");

        emailService.sendMail(email);
        ResponseDTO responseDTO = new ResponseDTO("Your profile has been Updated Successfully!", user, null);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to login user.
    @PostMapping("/login")
    public ResponseEntity <ResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        User user = service.checkLogin(loginDTO);
        ResponseDTO responseDTO = new ResponseDTO("Welcome " + user.getFirstName() + "! You are Successfully Logged In!", user, null);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to change user account password.
    @PutMapping("/changepwd")
    public ResponseEntity<ResponseDTO> changePassword(@Valid @RequestBody LoginDTO loginDTO, @RequestHeader String newPassword) {
        User user = service.updatePassword(loginDTO, newPassword);

        ResponseDTO responseDTO = new ResponseDTO("Password got changed successfully!", user);
        Email email = new Email(user.getEmail(), "Password Changed!", "Hello " + user.getFirstName() + "" +
                " Your 'OurBookStore' account password changed successfully!");
        emailService.sendMail(email);

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    // API to reset password.
    @PutMapping("/forgotpwd/{email}")
    public ResponseEntity<ResponseDTO> forgotPassword(
            @PathVariable String email, @RequestHeader String newPassword,
            @RequestHeader String confirmPassword
    ) {
        User user = service.resetPassword(email, newPassword, confirmPassword);
        ResponseDTO responseDTO = new ResponseDTO("Your password got reset successfully!", user);

        Email emailObject = new Email(user.getEmail(), "Password Reset!", "Hello " + user.getFirstName() + "" +
                " Your 'OurBookStore' account password reset successfully!");
        emailService.sendMail(emailObject);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }
}