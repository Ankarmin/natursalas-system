package com.natursalas.natursalassystem.service;

import com.natursalas.natursalassystem.model.dao.UserDAO;
import com.natursalas.natursalassystem.model.dto.UserDTO;

import java.sql.Connection;
import java.util.List;

public class UserService {
    private final UserDAO userDAO;

    public UserService(Connection connection) {
        this.userDAO = new UserDAO(connection);
    }

    public boolean addUser(UserDTO newUser) {
        return userDAO.addUser(newUser);
    }

    public boolean updateUser(UserDTO updatedUser) {
        return userDAO.updateUser(updatedUser);
    }

    public boolean deleteUser(String email) {
        return userDAO.deleteUser(email);
    }

    public UserDTO getUser(String email) {
        return userDAO.getUser(email);
    }

    public List<UserDTO> getAllUsers() {
        return userDAO.getAllUsers();
    }

    public boolean loginUser(String email, String password) {
        return userDAO.loginUser(email, password);
    }

    public UserDTO getUserDetails(String email) {
        return userDAO.getUserDetails(email);
    }
}