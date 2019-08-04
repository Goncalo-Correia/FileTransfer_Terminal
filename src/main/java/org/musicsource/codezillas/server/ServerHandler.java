package org.musicsource.codezillas.server;

import org.musicsource.codezillas.connection.Connection;
import org.musicsource.codezillas.server.persistence.Store;

public class ServerHandler {

    private Connection connection;
    private Store store;

    public ServerHandler() {
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void handleConnection() {

    }
}
