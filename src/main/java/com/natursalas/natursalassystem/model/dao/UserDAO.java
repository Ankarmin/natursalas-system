package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserDAO implements IUserDAO {
    private static final Logger LOGGER = Logger.getLogger(UserDAO.class.getName());
    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addUser(UserDTO newUser) {
        String query = "INSERT INTO user (email, password, role, idLocation) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newUser.getEmail());
            stmt.setString(2, newUser.getPassword());
            stmt.setString(3, newUser.getRole());
            stmt.setString(4, newUser.getIdLocation());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding user", e);
            return false;
        }
    }

    @Override
    public boolean updateUser(UserDTO updatedUser, String oldEmail) {
        String query = "UPDATE user SET email = ?, password = ?, role = ?, idLocation = ? WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, updatedUser.getEmail());
            stmt.setString(2, updatedUser.getPassword());
            stmt.setString(3, updatedUser.getRole());
            stmt.setString(4, updatedUser.getIdLocation());
            stmt.setString(5, oldEmail);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating user", e);
            return false;
        }
    }

    @Override
    public boolean deleteUser(String email) {
        if (!existsUser(email)) {
            LOGGER.log(Level.WARNING, "Attempted to delete non-existing user: {0}", email);
            return false;
        }
        String query = "DELETE FROM user WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting user", e);
            return false;
        }
    }

    @Override
    public UserDTO getUser(String email) {
        String query = "SELECT email, password, role, idLocation FROM user WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new UserDTO(rs.getString("email"), rs.getString("password"), rs.getString("role"), rs.getString("idLocation"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving user", e);
        }
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        String query = "SELECT email, password, role, idLocation FROM user";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                users.add(new UserDTO(rs.getString("email"), rs.getString("password"), rs.getString("role"), rs.getString("idLocation")));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all users", e);
        }
        return users;
    }

    @Override
    public boolean existsUser(String email) {
        String query = "SELECT COUNT(1) FROM user WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error checking if user exists", e);
            return false;
        }
    }
}