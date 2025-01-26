package com.natursalas.natursalassystem.model.dao;

import com.natursalas.natursalassystem.model.dto.LocationDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDAO implements ILocationDAO {
    private Connection connection;

    public LocationDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean addLocation(LocationDTO newLocation) {
        String query = "INSERT INTO location (idLocation, locationName, address, city) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newLocation.getIdLocation());
            stmt.setString(2, newLocation.getLocationName());
            stmt.setString(3, newLocation.getAddress());
            stmt.setString(4, newLocation.getCity());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateLocation(LocationDTO updatedLocation) {
        String query = "UPDATE location SET locationName = ?, address = ?, city = ? WHERE idLocation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, updatedLocation.getLocationName());
            stmt.setString(2, updatedLocation.getAddress());
            stmt.setString(3, updatedLocation.getCity());
            stmt.setString(4, updatedLocation.getIdLocation());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteLocation(String locationId) {
        String query = "DELETE FROM location WHERE idLocation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, locationId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public LocationDTO getLocation(String locationId) {
        String query = "SELECT * FROM location WHERE idLocation = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, locationId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new LocationDTO(rs.getString("idLocation"), rs.getString("locationName"), rs.getString("address"), rs.getString("city"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<LocationDTO> getAllLocations() {
        List<LocationDTO> locations = new ArrayList<>();
        String query = "SELECT * FROM location";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                locations.add(new LocationDTO(rs.getString("idLocation"), rs.getString("locationName"), rs.getString("address"), rs.getString("city")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return locations;
    }
}