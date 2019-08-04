package org.musicsource.codezillas.client;

import org.academiadecodigo.bootcamp.Prompt;
import org.musicsource.codezillas.connection.Connection;
import org.musicsource.codezillas.utils.Defaults;
import org.musicsource.codezillas.utils.Messages;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {

    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private Prompt prompt;
    private ClientHandler clientHandler;

    public Client(Prompt prompt) {
        this.prompt = prompt;
        clientHandler = new ClientHandler();
    }

    public void init() {
        try {
            System.out.println(Messages.BOOT_CLIENT);
            socket = new Socket(Defaults.LOCALHOST, Defaults.SERVER_PORT);
            setupStreams();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupStreams() throws IOException {
        System.out.println(Messages.CLIENT_STREAMS);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public void start() {
        System.out.println(Messages.CONNECTION);
        serverCommunication();
    }

    private void serverCommunication() {
        System.out.println(Messages.CONNECTED);
        while (socket.isBound()) {
            makeConnection();
            receiveConnection();
        }
    }

    private void makeConnection() {
        try {
            //outputStream.writeObject(connection);
            outputStream.flush();
            System.out.println(Messages.CONNECTION_COMPLETE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void receiveConnection() {
        Object object = null;
        try {
            object = inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (object instanceof Connection) {
            Connection connection = (Connection) object;
            clientHandler.setConnection(connection);
            clientHandler.setPrompt(prompt);
            clientHandler.handleConnection();
        }
    }

    public void shutdown() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
