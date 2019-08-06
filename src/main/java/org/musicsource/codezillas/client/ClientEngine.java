package org.musicsource.codezillas.client;

import org.musicsource.codezillas.connection.Connection;
import org.musicsource.codezillas.connection.ConnectionType;
import org.musicsource.codezillas.connection.commands.Command;
import org.musicsource.codezillas.connection.commands.CommandType;

public class ClientEngine {

    public ClientEngine() {
    }

    public Connection initConnection(Integer option){
        Connection connection = new Connection();
       switch (option) {
           case 1:
               connection.setConnectionType(ConnectionType.COMMAND);
               Command command = new Command();
               command.setCommandType(CommandType.LOGIN);
               connection.setCommand(command);
               break;
           case 2:
               connection.setConnectionType(ConnectionType.COMMAND);
               Command command1 = new Command();
               command1.setCommandType(CommandType.REGISTER);
               connection.setCommand(command1);
               break;
           case 3:
               connection.setConnectionType(ConnectionType.COMMAND);
               Command command2 = new Command();
               command2.setCommandType(CommandType.QUIT);
               connection.setCommand(command2);
               break;
       }
       return connection;
    }

    public Connection loginConnection(String[] client) {
        Connection connection = new Connection();
        connection.setConnectionType(ConnectionType.COMMAND);
        Command command = new Command();
        command.setCommandType(CommandType.MAIN);
        command.setMenuOptions(client);
        connection.setCommand(command);
        return connection;
    }

}
