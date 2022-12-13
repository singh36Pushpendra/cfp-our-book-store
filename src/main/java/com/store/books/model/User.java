package com.store.books.model;

import com.store.books.dto.LoginDTO;
import com.store.books.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

// Specifying a class as an entity, so that it can be mapped to a database table.
@Entity
// Generating a zeroth args constructor.
@NoArgsConstructor
// Generate getters for all instance variable of this class.
@Getter
public class User {

    // To make variable 'id' as primary key.
    @Id
    // To generate the primary key value automatically.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // To generate setter for id only.
    @Setter
    private int id;

    // Specifying a mapped column for 'email' and applying a unique constraint on it.
    @Column(unique = true)
    private String email;
    private String firstName, lastName, address;
    private LocalDate dob;

    // To generate setter for password only.
    @Setter
    private String password;

    // Constructor used in creation and updation of user.
    public User(UserDTO userDTO) {
        firstName = userDTO.firstName;
        lastName = userDTO.lastName;
        email = userDTO.email;
        address = userDTO.address;
        dob = userDTO.dob;
        password = userDTO.password;
    }

    // Constructor used in user login and in password change.
    public User(LoginDTO loginDTO) {
        email = loginDTO.email;
        password = loginDTO.password;
    }
}
