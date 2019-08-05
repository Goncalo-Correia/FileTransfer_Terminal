package org.musicsource.codezillas.server;

import org.musicsource.codezillas.connection.Connection;
import org.musicsource.codezillas.server.persistence.Store;

public class ServerHandler {

    private Connection connection;
    private Store store;
    private ServerEngine serverEngine;

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

    public void setServerEngine(ServerEngine serverEngine) {
        this.serverEngine = serverEngine;
    }

    public Connection handleConnection() {

        return null;
    }
}
