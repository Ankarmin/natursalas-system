package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.UserDTO;

import java.util.List;

public interface IUserDAO {
    boolean addUser(UserDTO newUser);

    boolean updateUser(UserDTO updatedUser, String oldEmail);

    boolean deleteUser(String email);

    UserDTO getUser(String email);

    List<UserDTO> getAllUsers();

    boolean existsUser(String email);
}