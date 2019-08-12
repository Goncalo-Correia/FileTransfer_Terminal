package org.musicsource.codezillas.server;

import org.musicsource.codezillas.connection.Request;
import org.musicsource.codezillas.connection.commands.CommandType;

import java.util.Map;

public class ServerHandler {

    private Request request;
    private ServerEngine serverEngine;
    private Map<String, String> usersMap;

    public ServerHandler() {
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void setServerEngine(ServerEngine serverEngine) {
        this.serverEngine = serverEngine;
        serverEngine.setUsersMap(usersMap);
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
        return serverEngine.initRequest();
    }

    private Request command() {
        CommandType commandType = this.request.getCommand().getCommandType();
        Request newRequest = new Request();
        switch (commandType) {
            case INIT:
                newRequest = serverEngine.initRequest();
                break;
            case LOGIN:
                newRequest = serverEngine.loginConnection(request);
                break;
            case CREDENTIALS:
                newRequest = serverEngine.credentialsConnection(request);
                break;
            case REGISTER:
                newRequest = serverEngine.registerConnection(request);
                break;
            case ADD_USER:
                newRequest = serverEngine.addUserConnection(request);
                break;
            case REBOOT:
                newRequest = serverEngine.mainConnection(request);
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
        return serverEngine.uploadFileConnection(request);
    }

    private Request download() {
        Request downloadRequest = new Request();
        switch (request.getCommand().getCommandType()) {
            case SERVER_FILES:
                downloadRequest = serverEngine.serverFileConnection(request);
                break;
            case DOWNLOAD:
                downloadRequest = serverEngine.downloadConnection(request);
                break;
        }
        return downloadRequest;
    }

}
