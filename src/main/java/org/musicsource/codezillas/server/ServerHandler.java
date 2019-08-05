package org.musicsource.codezillas.server;

import org.musicsource.codezillas.connection.Connection;
import org.musicsource.codezillas.connection.ConnectionType;
import org.musicsource.codezillas.connection.commands.Command;
import org.musicsource.codezillas.connection.commands.CommandType;
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
        Connection connection = null;
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
        Connection connection = new Connection();
        connection.setConnectionType(ConnectionType.COMMAND);

        Command command = new Command();
        command.setCommandType(CommandType.INIT);
        command.setMessage("Welcome");
        command.setMenuOptions(new String[]{"Login","Register","Quit"});

        connection.setCommand(command);
        connection.setTrack(null);

        return connection;
    }

    private Connection command() {
        return null;
    }

    private Connection upload() {
        return null;
    }

    private Connection download() {
        return null;
    }
}
