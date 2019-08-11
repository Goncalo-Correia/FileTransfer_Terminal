package org.musicsource.codezillas.client;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.musicsource.codezillas.connection.Request;

public class ClientHandler {

    private Request request;
    private Prompt prompt;
    private ClientEngine clientEngine;

    public ClientHandler() {
        clientEngine = new ClientEngine();
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
        clientEngine.setPrompt(prompt);
    }

    public Request handleConnection() {
        switch (request.getRequestType()) {
            case COMMAND:
                request = command();
                break;
            case UPLOAD:
                upload();
                break;
            case DOWNLOAD:
                request = download();
                break;
        }
        return request;
    }

    private Request command() {
        Request newRequest = null;
        switch (request.getCommand().getCommandType()) {
            case INIT:
            case REBOOT:
                Integer initOption = createMenu(request);
                newRequest = clientEngine.initConnection(initOption);
                break;
            case VALIDATE:
                String[] client = stringMenu(request);
                newRequest = clientEngine.validateConnection(client);
                break;
            case MAIN:
                Integer mainOption = createMenu(request);
                newRequest = clientEngine.mainConnection(mainOption);
                break;
            case NEW_USER:
                String[] register = stringMenu(request);
                newRequest = clientEngine.newUserConnection(register);
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

    private void upload() {

    }

    private Request download() {
        Request newRequest = new Request();
        switch (request.getCommand().getCommandType()) {
            case SERVER_FILES:
                Integer selectedFile = createMenu(request);

                break;
            case DOWNLOAD:
                break;
        }
        return newRequest;
    }

    private Integer createMenu(Request request) {
        MenuInputScanner menuScanner = new MenuInputScanner(request.getCommand().getMenuOptions());
        menuScanner.setMessage(request.getCommand().getMessage());
        return prompt.getUserInput(menuScanner);
    }

    private String[] stringMenu(Request request) {
        StringInputScanner stringScanner = new StringInputScanner();
        String[] answers = new String[request.getCommand().getMenuOptions().length];
        for (int i = 0; i < answers.length; i++) {
            stringScanner.setMessage(request.getCommand().getMenuOptions()[i]);
            answers[i] = prompt.getUserInput(stringScanner);
        }
        return answers;
    }
}
