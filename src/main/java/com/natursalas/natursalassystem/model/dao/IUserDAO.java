package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.UserDTO;

import java.util.List;

public interface IUserDAO {

    boolean addUser(UserDTO newUser);

    boolean updateUser(UserDTO updatedUser);

    boolean deleteUser(Integer userId);

    UserDTO getUser(Integer userId);

    List<UserDTO> getAllUsers();
}