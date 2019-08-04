package org.musicsource.codezillas.server;

import org.musicsource.codezillas.connection.Connection;

public class ServerHandler {

    private Connection connection;

    public ServerHandler() {
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void handleConnection() {

    }
}
