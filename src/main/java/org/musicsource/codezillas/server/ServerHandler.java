package org.musicsource.codezillas.server;

import org.musicsource.codezillas.connection.Request;
import org.musicsource.codezillas.connection.commands.CommandType;

import java.util.Map;

public class ServerHandler {

    private Request request;
    private ServerConnection serverConnection;
    private Map<String, String> usersMap;

    public ServerHandler() {
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void setServerConnection(ServerConnection serverConnection) {
        this.serverConnection = serverConnection;
        serverConnection.setUsersMap(usersMap);
    }

    public void setUsersMap(Map<String, String> usersMap) {
        this.usersMap = usersMap;
    }

    public Request handleConnection() {
        switch (request.getRequestType()) {
            case BOOT:
                request = boot();
                break;
            case COMMAND:
                request = command();
                break;
            case UPLOAD:
                request = upload();
                break;
            case DOWNLOAD:
                request = download();
                break;
        }
        return request;
    }

    private Request boot() {
        return serverConnection.initRequest();
    }

    private Request command() {
        CommandType commandType = this.request.getCommand().getCommandType();
        Request newRequest = new Request();
        switch (commandType) {
            case INIT:
                newRequest = serverConnection.initRequest();
                break;
            case LOGIN:
                newRequest = serverConnection.loginConnection(request);
                break;
            case CREDENTIALS:
                newRequest = serverConnection.credentialsConnection(request);
                break;
            case REGISTER:
                newRequest = serverConnection.registerConnection(request);
                break;
            case ADD_USER:
                newRequest = serverConnection.addUserConnection(request);
                break;
            case REBOOT:
                newRequest = serverConnection.mainConnection(request);
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
        return newRequest;
    }

    private Request upload() {
        return serverConnection.uploadFileConnection(request);
    }

    private Request download() {
        Request downloadRequest = new Request();
        switch (request.getCommand().getCommandType()) {
            case SERVER_FILES:
                downloadRequest = serverConnection.serverFileConnection(request);
                break;
            case DOWNLOAD:
                downloadRequest = serverConnection.downloadConnection(request);
                break;
        }
        return downloadRequest;
    }

}
