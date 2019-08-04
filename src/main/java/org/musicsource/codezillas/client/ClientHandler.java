package org.musicsource.codezillas.client;

import org.academiadecodigo.bootcamp.Prompt;
import org.musicsource.codezillas.client.views.View;
import org.musicsource.codezillas.connection.Connection;

import java.util.HashMap;
import java.util.Map;

public class ClientHandler {

    private Connection connection;
    private Prompt prompt;
    private Map<Integer, View> viewMap;

    public ClientHandler() {
        viewMap = new HashMap<>();
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }

    public void handleConnection() {

    }

}
