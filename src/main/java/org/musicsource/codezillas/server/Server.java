package org.musicsource.codezillas.server;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.musicsource.codezillas.connection.Request;
import org.musicsource.codezillas.utils.Defaults;
import org.musicsource.codezillas.utils.Messages;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ServerSocket serverSocket;
    private ExecutorService cachedPool;
    private Map<Integer, ConnectionHandler> connectionHandlerMap;
    private Map<String, String> usersMap;
    private Integer clientCount;
    private Prompt prompt;
    private boolean connected;
    private String userRoot;

    public Server() {
        cachedPool = Executors.newCachedThreadPool();
        connectionHandlerMap = Collections.synchronizedMap(new HashMap<Integer, ConnectionHandler>());
        usersMap = Collections.synchronizedMap(new HashMap<String, String>());
        usersMap.put("goncalo","ginasio1");
        clientCount = 0;
        prompt = new Prompt(System.in, System.out);
        userRoot = "";
    }

    public void control() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                StringInputScanner scanner = new StringInputScanner();
                scanner.setMessage("Close Server Message: shutdown\n\n\n");

                String option = prompt.getUserInput(scanner);
                if (option.equals("shutdown")) {
                    try {
                        connected = false;
                        serverSocket.close();
                        System.exit(0);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void init() {
        try {
            Integer serverPort = bootController();
            userRoot = bootMenu();
            System.out.println(Messages.BOOT_SERVER);
            serverSocket = new ServerSocket(serverPort);
            connected = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (connected) {
            try {
                System.out.println(Messages.WAIT_CONNECTION);

                Socket socket = serverSocket.accept();
                clientCount++;
                ConnectionHandler clientHandler = new ConnectionHandler(socket, usersMap, userRoot);

                connectionHandlerMap.put(clientCount, clientHandler);

                cachedPool.submit(clientHandler);
            } catch (IOException e) {
                System.out.println("closing server");
            }
        }
    }

    public void shutdown() {
        try {
            System.out.println(Messages.SERVER_CLOSE);
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer bootController() {
        System.out.println("Starting boot controller...");
        IntegerInputScanner scanner = new IntegerInputScanner();
        scanner.setMessage("SERVER port: ");
        return prompt.getUserInput(scanner);
    }

    private String bootMenu() {
        StringInputScanner strScanner = new StringInputScanner();
        strScanner.setMessage("SERVER root name: ");
        return prompt.getUserInput(strScanner);
    }

    private class ConnectionHandler implements Runnable {

        private Socket socket;
        private ObjectInputStream inputStream;
        private ObjectOutputStream outputStream;
        private ServerConnection serverConnection;
        private ServerFileManager serverFileManager;
        private ServerHandler serverHandler;
        private ServerRequest serverRequest;
        //private ServerService serverService;
        //private Map<String, String> userMap;
        private String userRoot;

        public ConnectionHandler(Socket socket, Map userMap, String userRoot) {
            this.socket = socket;
            //this.userMap = userMap;
            this.userRoot = userRoot;
            serverConnection = new ServerConnection();
            serverHandler = new ServerHandler();
            serverFileManager = new ServerFileManager(this.userRoot);
            serverRequest = new ServerRequest();
            //serverService = new ServerService();
        }

        private void wire() {
            serverFileManager.initServerDirectory();

            serverRequest.setServerFileManager(serverFileManager);

            serverConnection.setUsersMap(usersMap);
            serverConnection.setServerFileManager(serverFileManager);
            serverConnection.setServerRequest(serverRequest);

            serverHandler.setServerConnection(serverConnection);
        }

        private void setupStreams() {
            try {
                System.out.println(Messages.SERVER_STREAMS);
                inputStream = new ObjectInputStream(socket.getInputStream());
                outputStream = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            System.out.println(Messages.CLIENT_CONNECTED);
            wire();
            setupStreams();
            clientCommunication();
            shutdown();
        }

        private void clientCommunication() {
            while (socket.isBound()) {
                Request request = receiveConnection();

                Request newRequest = handleConnection(request);

                sendConnection(newRequest);
            }
        }

        private Request receiveConnection() {
            Object object = null;
            Request request = null;

            try {
                object = inputStream.readObject();
            } catch (EOFException ex) {
                ex.getMessage();
                System.out.println(Thread.currentThread().getName() + " Client disconnected");
            } catch (ClassNotFoundException e) {
                e.getMessage();
                System.out.println(Thread.currentThread().getName() + " Client disconnected");
            } catch (IOException e) {
                e.getMessage();
                System.out.println(Thread.currentThread().getName() + " Client disconnected");
            }

            if (object instanceof Request) {
                request = (Request) object;
            }
            return request;
        }

        private Request handleConnection(Request request) {

            serverHandler.setRequest(request);
            serverHandler.setUsersMap(usersMap);

            return serverHandler.handleConnection();
        }

        private void sendConnection(Request request) {

            try {
                outputStream.writeObject(request);
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void shutdown() {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
