package org.musicsource.codezillas.server;

import org.musicsource.codezillas.connection.Request;
import org.musicsource.codezillas.connection.RequestType;
import org.musicsource.codezillas.connection.commands.Command;
import org.musicsource.codezillas.connection.commands.CommandType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class ServerEngine {

    private Map<String,String> usersMap;
    private ServerServices serverServices;
    private ServerRequests serverRequests;

    public ServerEngine() {
        serverServices = new ServerServices();
        serverRequests = new ServerRequests();
    }

    public Request initRequest() {
        return serverRequests.initRequest();
    }

    public Request loginConnection(Request request) {
        return serverRequests.loginRequest(request);
    }

    public Request mainConnection(Request request) {
        return serverRequests.mainRequest(request);
    }

    public Request credentialsConnection(Request request) {

        if (authenticate(request.getCommand().getMenuOptions()[0], request.getCommand().getMenuOptions()[1])) {
            return serverRequests.mainRequest(request);
        }
        return serverRequests.rebootRequest(request);
    }

    private boolean authenticate(String username, String password) {
        if (usersMap.get(username).equals(password)) {
            return true;
        }
        return false;
    }

    public void setUsersMap(Map<String, String> usersMap) {
        this.usersMap = usersMap;
    }

    public Request registerConnection(Request request) {
        return serverRequests.registerRequest(request);
    }

    public Request addUserConnection(Request request) {
        String username = request.getCommand().getMenuOptions()[0];
        String password = request.getCommand().getMenuOptions()[1];
        if (usersMap.get(username) != null) {

        }
        usersMap.put(username, password);
        Request request1 = new Request();
        request1.setRequestType(RequestType.COMMAND);
        Command command = new Command();
        command.setCommandType(CommandType.VALIDATE);
        request1.setCommand(command);
        return request1;
    }
}
