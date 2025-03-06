package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.LocationDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LocationDAO implements ILocationDAO {
    private static final Logger LOGGER = Logger.getLogger(LocationDAO.class.getName());
    private final Connection connection;

    public LocationDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addLocation(LocationDTO newLocation) {
        String query = "INSERT INTO location (idLocation, address) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newLocation.getIdLocation());
            stmt.setString(2, newLocation.getAddress());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error adding location", e);
            return false;
        }
    }

    @Override
    public boolean updateLocation(LocationDTO updatedLocation) {
        String query = "UPDATE location SET address = ? WHERE idLocation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, updatedLocation.getAddress());
            stmt.setString(2, updatedLocation.getIdLocation());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating location", e);
            return false;
        }
    }

    @Override
    public boolean deleteLocation(String locationId) {
        if (!exists(locationId)) {
            LOGGER.log(Level.WARNING, "Attempted to delete non-existing location: {0}", locationId);
            return false;
        }
        String query = "DELETE FROM location WHERE idLocation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, locationId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error deleting location", e);
            return false;
        }
    }

    @Override
    public LocationDTO getLocation(String locationId) {
        String query = "SELECT idLocation, address FROM location WHERE idLocation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, locationId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new LocationDTO(rs.getString("idLocation"), rs.getString("address"));
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving location", e);
        }
        return null;
    }

    @Override
    public List<LocationDTO> getAllLocations() {
        List<LocationDTO> locations = new ArrayList<>();
        String query = "SELECT idLocation, address FROM location";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                locations.add(new LocationDTO(rs.getString("idLocation"), rs.getString("address")));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving all locations", e);
        }
        return locations;
    }

    @Override
    public boolean exists(String idLocation) {
        String query = "SELECT COUNT(1) FROM location WHERE idLocation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idLocation);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error checking if location exists", e);
        }
        return false;
    }
}