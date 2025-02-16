package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
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
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(UserDTO updatedUser) {
        String query = "UPDATE user SET password = ?, role = ?, idLocation = ? WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, updatedUser.getPassword());
            stmt.setString(2, updatedUser.getRole());
            stmt.setString(3, updatedUser.getIdLocation());
            stmt.setString(4, updatedUser.getEmail());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteUser(String email) {
        String query = "DELETE FROM user WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserDTO getUser(String email) {
        String query = "SELECT * FROM user WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UserDTO(rs.getString("email"), rs.getString("password"), rs.getString("role"), rs.getString("idLocation"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        String query = "SELECT * FROM user";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                users.add(new UserDTO(rs.getString("email"), rs.getString("password"), rs.getString("role"), rs.getString("idLocation")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public boolean loginUser(String email, String password) {
        String query = "SELECT * FROM user WHERE email = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public UserDTO getUserDetails(String email) {
        String query = "SELECT email, password, role, idLocation FROM user WHERE email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new UserDTO(rs.getString("email"), rs.getString("password"), rs.getString("role"), rs.getString("idLocation"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}