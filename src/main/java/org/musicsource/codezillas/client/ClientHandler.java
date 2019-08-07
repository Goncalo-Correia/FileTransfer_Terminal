package org.musicsource.codezillas.client;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.musicsource.codezillas.connection.Connection;

public class ClientHandler {

    private Connection connection;
    private Prompt prompt;
    private ClientEngine clientEngine;

    public ClientHandler() {
        clientEngine = new ClientEngine();
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }

    public Connection handleConnection() {
        switch (connection.getConnectionType()) {
            case COMMAND:
                connection = command();
                break;
            case UPLOAD:
                upload();
                break;
            case DOWNLOAD:
                download();
                break;
        }
        return connection;
    }

    private Connection command() {
        Connection newConnection = null;
        switch (connection.getCommand().getCommandType()) {
            case INIT:
                Integer initOption = createMenu(connection);
                newConnection = clientEngine.initConnection(initOption);
                break;
            case VALIDATE:
                String[] client = stringMenu(connection);
                newConnection = clientEngine.validateConnection(client);
                break;
            case MAIN:
                Integer mainOption = createMenu(connection);
                newConnection = clientEngine.mainConnection(mainOption);
                break;
            case NEW_USER:
                String[] register = stringMenu(connection);
                newConnection = clientEngine.newUserConnection(register);
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
        return newConnection;
    }

    private void upload() {

    }

    private void download() {

    }

    private Integer createMenu(Connection connection) {
        MenuInputScanner menuScanner = new MenuInputScanner(connection.getCommand().getMenuOptions());
        menuScanner.setMessage(connection.getCommand().getMessage());
        return prompt.getUserInput(menuScanner);
    }

    private String[] stringMenu(Connection connection) {
        StringInputScanner stringScanner = new StringInputScanner();
        String[] answers = new String[connection.getCommand().getMenuOptions().length];
        for (int i = 0; i < answers.length; i++) {
            stringScanner.setMessage(connection.getCommand().getMenuOptions()[i]);
            answers[i] = prompt.getUserInput(stringScanner);
        }
        return answers;
    }
}
