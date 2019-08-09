package org.musicsource.codezillas.server;

import org.musicsource.codezillas.connection.Request;
import org.musicsource.codezillas.connection.commands.CommandType;
import org.musicsource.codezillas.server.persistence.Store;

import java.util.Map;

public class ServerHandler {

    private Request request;
    private Store store;
    private ServerEngine serverEngine;
    private Map<String, String> usersMap;

    public ServerHandler() {
    }

    public void setRequest(Request request) {
        this.request = request;
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
        return newRequest;
    }

    private Request upload() {
        return null;
    }

    private Request download() {
        return null;
    }

}
