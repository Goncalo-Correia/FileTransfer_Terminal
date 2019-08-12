package org.musicsource.codezillas.client;

import org.musicsource.codezillas.connection.Request;
import org.musicsource.codezillas.connection.RequestType;
import org.musicsource.codezillas.connection.commands.Command;
import org.musicsource.codezillas.connection.commands.CommandType;
import org.musicsource.codezillas.server.persistence.models.Track;

public class ClientRequest {

    public Request loginRequest() {
        Request request = new Request();
        request.setRequestType(RequestType.COMMAND);
        Command command = new Command();
        command.setCommandType(CommandType.LOGIN);
        request.setCommand(command);
        return request;
    }

    public Request registerRequest() {
        Request request = new Request();
        request.setRequestType(RequestType.COMMAND);
        Command command = new Command();
        command.setCommandType(CommandType.REGISTER);
        request.setCommand(command);
        return request;
    }

    public Request validateRequest(String[] client) {
        Request request = new Request();
        request.setRequestType(RequestType.COMMAND);
        Command command = new Command();
        command.setCommandType(CommandType.CREDENTIALS);
        command.setMenuOptions(client);
        request.setCommand(command);
        return request;
    }

    public Request newUserRequest(String[] registerOptions) {
        Request request = new Request();
        request.setRequestType(RequestType.COMMAND);
        Command command = new Command();
        command.setCommandType(CommandType.ADD_USER);
        command.setMenuOptions(registerOptions);
        request.setCommand(command);
        return request;
    }

    public Request updateRequest() {
        Request request = new Request();
        request.setRequestType(RequestType.COMMAND);
        Command command = new Command();
        command.setCommandType(CommandType.UPDATE);
        request.setCommand(command);
        return request;
    }

    public Request uploadRequest(String fileInfo) {
        Request request = new Request();
        request.setRequestType(RequestType.UPLOAD);
        Command command = new Command();
        command.setCommandType(CommandType.UPLOAD);
        request.setCommand(command);
        Track track = new Track();
        track.setTrackData(fileInfo);
        request.setTrack(track);
        return request;
    }

    public Request serverFilesRequest() {
        Request request = new Request();
        request.setRequestType(RequestType.DOWNLOAD);
        Command command = new Command();
        command.setCommandType(CommandType.SERVER_FILES);
        request.setCommand(command);
        return request;
    }

    public Request downloadRequest(String fileName) {
        Request request = new Request();
        request.setRequestType(RequestType.DOWNLOAD);
        Command command = new Command();
        command.setCommandType(CommandType.DOWNLOAD);
        command.setMessage(fileName);
        request.setCommand(command);
        return request;
    }

    public Request backToMainRequest() {
        Request request = new Request();
        request.setRequestType(RequestType.COMMAND);
        Command command = new Command();
        command.setCommandType(CommandType.REBOOT);
        request.setCommand(command);
        return request;
    }

    public Request quitRequest() {
        Request request = new Request();
        request.setRequestType(RequestType.COMMAND);
        Command command = new Command();
        command.setCommandType(CommandType.INIT);
        request.setCommand(command);
        return request;
    }

}
