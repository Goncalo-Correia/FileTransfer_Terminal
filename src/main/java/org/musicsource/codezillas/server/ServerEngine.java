package org.musicsource.codezillas.server;

import org.musicsource.codezillas.connection.Connection;
import org.musicsource.codezillas.connection.ConnectionType;
import org.musicsource.codezillas.connection.commands.Command;
import org.musicsource.codezillas.connection.commands.CommandType;

import java.util.Map;

public class ServerEngine {

    private Map<String,String> usersMap;

    public Connection initConnection() {
        Connection connection = new Connection();
        connection.setConnectionType(ConnectionType.COMMAND);

        Command command = new Command();
        command.setCommandType(CommandType.INIT);
        command.setMessage("Welcome to Music Source");
        command.setMenuOptions(new String[]{"Login","Register","Quit"});

        connection.setCommand(command);
        connection.setTrack(null);
        return connection;
    }

    public Connection loginConnection(Connection connection) {
        connection.setConnectionType(ConnectionType.COMMAND);
        Command command = new Command();
        command.setCommandType(CommandType.VALIDATE);
        command.setMenuOptions(new String[]{"Insert username: ","Insert password: "});
        connection.setCommand(command);
        return connection;
    }

    public Connection mainConnection(Connection connection) {
        connection.setConnectionType(ConnectionType.COMMAND);
        Command command = new Command();
        command.setCommandType(CommandType.MAIN);
        command.setMessage("Client Profile: ");
        String[] client = new String[] {"Client Info","Upload","Download","Quit"};
        command.setMenuOptions(client);
        connection.setCommand(command);
        return connection;
    }

    public Connection credentialsConnection(Connection connection) {
        /*
        if (authenticate(connection.getCommand().getMenuOptions()[0], connection.getCommand().getMenuOptions()[1])) {
            return mainConnection(connection);
        }
        return initConnection();
        */
        return mainConnection(connection);
    }

    private boolean authenticate(String username, String password) {
        if (usersMap.get(username) != null && usersMap.get(username).equals(password)) {
            return true;
        }
        return false;
    }

    public void setUsersMap(Map<String, String> usersMap) {
        this.usersMap = usersMap;
    }

    public Connection registerConnection(Connection connection) {
        connection.setConnectionType(ConnectionType.COMMAND);
        Command command = new Command();
        command.setCommandType(CommandType.NEW_USER);
        command.setMenuOptions(new String[]{"Register username: ","Register password: "});
        connection.setCommand(command);
        return connection;
    }

    public Connection addUserConnection(Connection connection) {
        usersMap.put(connection.getCommand().getMenuOptions()[0],connection.getCommand().getMenuOptions()[1]);
        Connection connection1 = new Connection();
        connection1.setConnectionType(ConnectionType.COMMAND);
        Command command = new Command();
        command.setCommandType(CommandType.VALIDATE);
        connection1.setCommand(command);
        return connection1;
    }
}
