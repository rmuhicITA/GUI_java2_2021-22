package com.app_with_db.dao;

import com.app_with_db.connection.ConnectionPool;
import com.app_with_db.model.City;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class CityDao implements Dao<City>{

    private ConnectionPool connectionPool;

    public CityDao(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void create(City entity) throws SQLException {

    }

    @Override
    public City retrieve(int id) throws SQLException {
        String sqlQuery = "SELECT * FROM city WHERE city_id =?";
        Connection connection = connectionPool.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        try {
            while(resultSet.next()) {
                City city = new City();
                city.setCityId(resultSet.getInt(1));
                city.setCity(resultSet.getString(2));
                city.setCountry_id(resultSet.getInt(3));
                city.setLast_update(resultSet.getTimestamp(4));
                return city;
            }
        }catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<City> retrieveAll() throws SQLException {
        List<City> cities = new ArrayList<>();
        String sqlQuery = "SELECT * FROM city";
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();) {
            while(resultSet.next()) {
                City city = new City();
                city.setCityId(resultSet.getInt(1));
                city.setCity(resultSet.getString(2));
                city.setCountry_id(resultSet.getInt(3));
                city.setLast_update(resultSet.getTimestamp(4));
                cities.add(city);
            }
        }catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
        }
        connectionPool.releaseConnection(connection);
        return cities;
    }

    @Override
    public void update(City entity) throws SQLException {
        String sqlQuery = "UPDATE city SET city = ? WHERE city_id = ?";
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);) {
            preparedStatement.setString(1, entity.getCity());
            preparedStatement.setInt(2, entity.getCityId());
            int count = preparedStatement.executeUpdate();
            System.out.println("Broj updatovanih objekata je "+count);
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
        }
    }

    @Override
    public void delete(City entity) throws SQLException {
        /*String sqlQuery = "DELETE FROM city WHERE city_id = ?";
        Connection connection = connectionPool.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);) {
            preparedStatement.setInt(1, entity.getCityId());
            int count = preparedStatement.executeUpdate();
            System.out.println("Broj izbrisanih objekata je "+count);
        } catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
        }*/
    }

    public Vector<String> getColumnNames() {
        Vector<String> columnNames = new Vector<>();
        String sqlQuery = "SELECT * FROM city";
        Connection connection = connectionPool.getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnNumbers = metaData.getColumnCount();
            for(int i=1; i<= columnNumbers; i++) {
                String columName = metaData.getColumnName(i);
                columnNames.add(columName);
            }
        }catch (SQLException sqlException) {
            System.err.println(sqlException.getMessage());
        }
        return columnNames;
    }
}
