package org.musicsource.codezillas.server;

import org.musicsource.codezillas.connection.Request;
import org.musicsource.codezillas.connection.RequestType;
import org.musicsource.codezillas.connection.commands.Command;
import org.musicsource.codezillas.connection.commands.CommandType;

public class ServerRequests {

    private ServerFileManager serverFileManager;

    public ServerRequests() {
        serverFileManager = new ServerFileManager();
    }

    public Request initRequest() {
        Request request = new Request();
        request.setRequestType(RequestType.COMMAND);

        Command command = new Command();
        command.setCommandType(CommandType.INIT);
        command.setMessage("Welcome to Music Source");
        command.setMenuOptions(new String[]{"Login","Register","Quit"});

        request.setCommand(command);
        request.setTrack(null);
        return request;
    }

    public Request loginRequest(Request request) {
        request.setRequestType(RequestType.COMMAND);
        Command command = new Command();
        command.setCommandType(CommandType.VALIDATE);
        command.setMenuOptions(new String[]{"Insert username: ","Insert password: "});
        request.setCommand(command);
        return request;
    }

    public Request mainRequest(Request request) {
        request.setRequestType(RequestType.COMMAND);
        Command command = new Command();
        command.setCommandType(CommandType.MAIN);
        command.setMessage("Client Profile: ");
        String[] client = new String[] {"Client Info","Upload","Download","Quit"};
        command.setMenuOptions(client);
        request.setCommand(command);
        return request;
    }

    public Request registerRequest(Request request) {
        request.setRequestType(RequestType.COMMAND);
        Command command = new Command();
        command.setCommandType(CommandType.NEW_USER);
        command.setMenuOptions(new String[]{"Register username: ","Register password: "});
        request.setCommand(command);
        return request;
    }

    public Request rebootRequest(Request request) {
        request.setRequestType(RequestType.COMMAND);
        Command command = new Command();
        command.setCommandType(CommandType.REBOOT);
        command.setMessage("Welcome to Music Source");
        command.setMenuOptions(new String[]{"Login","Register","Quit"});
        request.setCommand(command);
        return request;
    }

}
