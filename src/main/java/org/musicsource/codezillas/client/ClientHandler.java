package org.musicsource.codezillas.client;

import org.academiadecodigo.bootcamp.Prompt;
import org.musicsource.codezillas.client.views.*;
import org.musicsource.codezillas.connection.Connection;
import org.musicsource.codezillas.connection.ConnectionType;
import org.musicsource.codezillas.connection.commands.Command;
import org.musicsource.codezillas.connection.commands.CommandType;

import java.util.HashMap;
import java.util.Map;

public class ClientHandler {

    private Connection connection;
    private Prompt prompt;
    private Map<Integer, View> viewMap;
    private ClientEngine clientEngine;

    public ClientHandler() {
        viewMap = new HashMap<>();
        clientEngine = new ClientEngine();
        initMap();
    }

    private void initMap() {
        viewMap.put(1, new InitView());
        viewMap.put(2, new LoginView());
        viewMap.put(3, new RegisterView());
        viewMap.put(4, new MainMenuView());
        viewMap.put(5, new UpdateView());
        viewMap.put(6, new UploadView());
        viewMap.put(7, new DownloadView());
        viewMap.put(8, new QuitView());
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }

    public Connection handleConnection() {
        Connection newConnection = null;
        switch (connection.getConnectionType()) {
            case BOOT:
                System.out.println(connection.getCommand().getMessage());
                newConnection = boot();
                break;
            case COMMAND:
                newConnection = command();
                break;
            case UPLOAD:
                upload();
                break;
            case DOWNLOAD:
                download();
                break;
        }
        return newConnection;
    }

    private Connection boot() {
        Connection connection = new Connection();
        connection.setConnectionType(ConnectionType.COMMAND);
        connection.setTrack(null);

        Command command = new Command();
        command.setCommandType(CommandType.BOOT);
        command.setMessage(null);
        command.setMenuOptions(null);

        connection.setCommand(command);
        return connection;
    }

    private Connection command() {
        CommandType commandType = connection.getCommand().getCommandType();
        View view = null;
        Connection connection = null;
        switch (commandType) {
            case INIT:
                view = viewMap.get(1);
                view.show();
                break;
            case LOGIN:
                view = viewMap.get(2);
                view.show();
                break;
            case REGISTER:
                view = viewMap.get(3);
                view.show();
                break;
            case MAIN:
                view = viewMap.get(4);
                view.show();
                break;
            case UPDATE:
                view = viewMap.get(5);
                view.show();
                break;
            case UPLOAD:
                view = viewMap.get(6);
                view.show();
                break;
            case DOWNLOAD:
                view = viewMap.get(7);
                view.show();
                break;
            case QUIT:
                view = viewMap.get(8);
                view.show();
                break;
        }
        return connection;
    }

    private void upload() {

    }

    private void download() {

    }
}
