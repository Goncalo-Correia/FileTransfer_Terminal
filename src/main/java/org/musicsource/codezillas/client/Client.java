package org.musicsource.codezillas.client;

import org.academiadecodigo.bootcamp.Prompt;
import org.musicsource.codezillas.connection.Connection;
import org.musicsource.codezillas.connection.ConnectionType;
import org.musicsource.codezillas.connection.commands.Command;
import org.musicsource.codezillas.connection.commands.CommandType;
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

        makeConnection(initialConenction());
        System.out.println(Messages.CONNECTION_COMPLETE);

        while (socket.isBound()) {
            Connection connection = receiveConnection();

            Connection responseConnection = handleConnection(connection);

            makeConnection(responseConnection);
        }
    }

    private Connection initialConenction() {
        Connection bootConnection = new Connection();
        bootConnection.setConnectionType(ConnectionType.BOOT);

        Command command = new Command();
        command.setCommandType(CommandType.BOOT);
        command.setMenuOptions(null);
        command.setMessage(null);
        bootConnection.setCommand(command);

        bootConnection.setTrack(null);

        System.out.println(Messages.NEW_CONNECTION);
        return bootConnection;
    }

    private void makeConnection(Connection connection) {
        try {
            outputStream.writeObject(connection);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Connection receiveConnection() {
        Object object = null;
        try {
            object = inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection connection = null;
        if (object instanceof Connection) {
            connection = (Connection) object;
        }
        return connection;
    }

    private Connection handleConnection(Connection connection) {

        clientHandler.setConnection(connection);
        clientHandler.setPrompt(prompt);

        return clientHandler.handleConnection();
    }

    public void shutdown() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
