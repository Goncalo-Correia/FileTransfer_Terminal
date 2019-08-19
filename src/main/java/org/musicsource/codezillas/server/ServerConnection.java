package org.musicsource.codezillas.server;

import org.musicsource.codezillas.connection.Request;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerConnection {

    private Map<String,String> usersMap;
    private ServerService serverService;
    private ServerRequest serverRequest;
    private ServerFileManager serverFileManager;

    public ServerConnection(Socket socket) {
        usersMap = new HashMap<>();
        serverService = new ServerService();
        serverRequest = new ServerRequest();
        serverFileManager = new ServerFileManager();
    }

    public Request initRequest() {
        return serverRequest.initRequest();
    }

    public Request loginConnection(Request request) {
        return serverRequest.loginRequest(request);
    }

    public Request credentialsConnection(Request request) {
        String username = request.getCommand().getMenuOptions()[0];
        String password = request.getCommand().getMenuOptions()[1];
        if (usersMap.get(username) != null && usersMap.get(username).equals(password)) {
            return serverRequest.mainRequest(request);
        }
        return serverRequest.rebootRequest(request);
    }

    public void setUsersMap(Map<String, String> usersMap) {
        this.usersMap = usersMap;
    }

    public Request registerConnection(Request request) {
        return serverRequest.registerRequest(request);
    }

    public Request addUserConnection(Request request) {
        String username = request.getCommand().getMenuOptions()[0];
        String password = request.getCommand().getMenuOptions()[1];
        if (usersMap.get(username) == null) {
            usersMap.put(username, password);
            return serverRequest.mainRequest(request);
        }
        return serverRequest.registerRequest(request);
    }

    public Request mainConnection(Request request) {
        return serverRequest.mainRequest(request);
    }

    public Request updateConnection(Request request) {
        return serverRequest.updateRequest(request);
    }

    public Request uploadFileConnection(Request request) {
        byte[] fileData = request.getTrack().getTrackData();
        String fileName = request.getTrack().getFileName();
        serverFileManager.uploadFile(fileData, fileName);
        return serverRequest.mainRequest(request);
    }

    public Request serverFileConnection(Request request) {
        return serverRequest.serverFileRequest(request);
    }

    public Request downloadConnection(Request request) {
        return serverRequest.downloadFileRequest(request);
    }
}
