package com.app_with_db.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {
    private final int NUMBER_OF_CONNECTIONS = 10;

    private List<Connection> availableConnections = new ArrayList<>();
    private List<Connection> usedConnections = new ArrayList<>();

    public ConnectionPool() throws SQLException{
        for (int i = 0; i < NUMBER_OF_CONNECTIONS; i++) {
            Connection connection = createConnection();
            availableConnections.add(connection);
        }
    }

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection(
                ConnectionProperties.URL.getValue(),
                ConnectionProperties.USERNAME.getValue(),
                ConnectionProperties.PASSWORD.getValue());
    }

    public Connection getConnection() {
        if(availableConnections.isEmpty()){
            throw new RuntimeException("Nema dostupnih konekcija");
        } else {
            Connection connection = availableConnections.get(availableConnections.size()-1);
            availableConnections.remove(connection);
            usedConnections.add(connection);
            return connection;
        }
    }

    public boolean releaseConnection(Connection connection) {
        if(null != connection) {
            usedConnections.remove(connection);
            availableConnections.add(connection);
            return true;
        }else {
            return false;
        }
    }
}
