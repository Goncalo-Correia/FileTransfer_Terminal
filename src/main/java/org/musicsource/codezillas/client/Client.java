package org.musicsource.codezillas.client;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.musicsource.codezillas.connection.Request;
import org.musicsource.codezillas.connection.RequestType;
import org.musicsource.codezillas.connection.commands.Command;
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
    private ClientConnection clientConnection;
    private ClientFileManager clientFileManager;
    private ClientRequest clientRequest;
    private Request request;
    private String userRoot;

    public Client(Prompt prompt) {
        this.prompt = prompt;
        userRoot = bootDirectory();
        clientHandler = new ClientHandler();
        clientConnection = new ClientConnection();
        clientFileManager = new ClientFileManager(userRoot);
        clientRequest = new ClientRequest();
        request = new Request();
    }

    public void wire() {
        //userRoot = bootDirectory();
        //clientFileManager.setUserRoot(userRoot);
        clientFileManager.initClientDirectory();

        clientConnection.setPrompt(prompt);
        clientConnection.setClientFileManager(clientFileManager);
        clientConnection.setClientRequest(clientRequest);

        clientHandler.setClientConnection(clientConnection);
    }

    public void init() {
        try {
            String host = bootHost();
            Integer port = bootPort();
            socket = new Socket(host, port);
            setupStreams();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupStreams() throws IOException {
        System.out.println("\n" + Messages.CLIENT_STREAMS);
        outputStream = new ObjectOutputStream(socket.getOutputStream());
        inputStream = new ObjectInputStream(socket.getInputStream());
    }

    public void start() {
        makeConnection(initialConnection());
        System.out.println(Messages.CONNECTION_COMPLETE);

        //while (socket.isBound()) {
        while (true) {
            Request request = receiveConnection();

            Request responseRequest = handleConnection(request);

            makeConnection(responseRequest);
        }
    }

    private Request initialConnection() {
        Request bootRequest = new Request();
        bootRequest.setRequestType(RequestType.BOOT);

        Command command = new Command();
        command.setCommandType(null);
        command.setMenuOptions(null);
        command.setMessage(null);
        bootRequest.setCommand(command);

        bootRequest.setTrack(null);

        System.out.println(Messages.NEW_CONNECTION);
        return bootRequest;
    }

    private void makeConnection(Request request) {
        try {
            outputStream.writeObject(request);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Request receiveConnection() {
        try {
            request = (Request) inputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return request;
    }

    private Request handleConnection(Request request) {

        clientHandler.setRequest(request);
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

    private Integer bootPort() {
        IntegerInputScanner intScanner = new IntegerInputScanner();
        intScanner.setMessage("\nConnecting SERVER port: ");
        return prompt.getUserInput(intScanner);
    }

    private String bootHost(){
        StringInputScanner strScanner = new StringInputScanner();
        strScanner.setMessage("\nSERVER IP or 'localhost'\nCLIENT host address: ");
        return prompt.getUserInput(strScanner);
    }

    private String bootDirectory() {
        StringInputScanner strScanner = new StringInputScanner();
        strScanner.setMessage("\n'INFO: computer username'\nCLIENT root name: ");
        return prompt.getUserInput(strScanner);
    }
}
