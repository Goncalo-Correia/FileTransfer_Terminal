package org.musicsource.codezillas.server;

import org.musicsource.codezillas.connection.Connection;
import org.musicsource.codezillas.connection.ConnectionType;
import org.musicsource.codezillas.connection.commands.Command;
import org.musicsource.codezillas.connection.commands.CommandType;
import org.musicsource.codezillas.server.persistence.Store;

import java.util.Map;

public class ServerHandler {

    private Connection connection;
    private Store store;
    private ServerEngine serverEngine;
    private Map<String, String> usersMap;

    public ServerHandler() {
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public void setServerEngine(ServerEngine serverEngine) {
        this.serverEngine = serverEngine;
        serverEngine.setUsersMap(usersMap);
    }

    public void setUsersMap(Map<String, String> usersMap) {
        this.usersMap = usersMap;
    }

    public Connection handleConnection() {
        switch (connection.getConnectionType()) {
            case BOOT:
                connection = boot();
                break;
            case COMMAND:
                connection = command();
                break;
            case UPLOAD:
                connection = upload();
                break;
            case DOWNLOAD:
                connection = download();
                break;
        }
        return connection;
    }

    private Connection boot() {
        return serverEngine.initConnection();
    }

    private Connection command() {
        CommandType commandType = connection.getCommand().getCommandType();
        Connection connection = new Connection();
        switch (commandType) {
            case INIT:
                break;
            case LOGIN:
                connection = serverEngine.loginConnection(connection);
                break;
            case CREDENTIALS:
                connection = serverEngine.credentialsConnection(connection);
                break;
            case REGISTER:
                connection = serverEngine.registerConnection(connection);
                break;
            case ADD_USER:
                connection = serverEngine.addUserConnection(connection);
                break;
            case MAIN:
                break;
            case UPDATE:
                break;
            case UPLOAD:
                break;
            case DOWNLOAD:
                break;
            case QUIT:
                break;
        }
        return connection;
    }

    private Connection upload() {
        return null;
    }

    private Connection download() {
        return null;
    }

    private boolean validateLogin(Connection connection) {
        String username = connection.getCommand().getMenuOptions()[0];
        String password = connection.getCommand().getMenuOptions()[1];

        if (usersMap.containsKey(username) && usersMap.containsValue(password)) {
            return true;
        }
        return false;
    }
}
