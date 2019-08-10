package org.musicsource.codezillas.server;

import org.musicsource.codezillas.connection.Request;
import org.musicsource.codezillas.utils.Defaults;
import org.musicsource.codezillas.utils.Messages;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ServerSocket serverSocket;
    private ExecutorService cachedPool;
    private Map<Integer, ConnectionHandler> connectionHandlerMap;
    private Map<String, String> usersMap;
    private Integer clientCount;
    private ServerEngine serverEngine;

    public Server() {
        cachedPool = Executors.newCachedThreadPool();
        connectionHandlerMap = Collections.synchronizedMap(new HashMap<Integer, ConnectionHandler>());
        usersMap = Collections.synchronizedMap(new HashMap<String, String>());
        usersMap.put("goncalo","ginasio1");
        clientCount = 0;
        serverEngine = new ServerEngine();
    }

    public void init() {
        try {
            System.out.println(Messages.BOOT_SERVER);
            serverSocket = new ServerSocket(Defaults.SERVER_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (serverSocket.isBound()) {
            try {
                System.out.println(Messages.WAIT_CONNECTION);

                Socket socket = serverSocket.accept();
                clientCount++;
                ConnectionHandler clientHandler = new ConnectionHandler(socket, serverEngine, usersMap);

                connectionHandlerMap.put(clientCount, clientHandler);

                cachedPool.submit(clientHandler);
            } catch (IOException e) {
                e.printStackTrace();
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

    private class ConnectionHandler implements Runnable {

        private Socket socket;
        private ObjectInputStream inputStream;
        private ObjectOutputStream outputStream;
        private ServerHandler serverHandler;
        private ServerEngine serverEngine;
        private Map<String, String> userMap;

        public ConnectionHandler(Socket socket, ServerEngine serverEngine, Map userMap) {
            this.socket = socket;
            this.serverEngine = serverEngine;
            setupStreams();
            serverHandler = new ServerHandler();
            this.userMap = userMap;
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
            clientCommunication();
        }

        private void clientCommunication() {
            while (serverSocket.isBound()) {
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
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (object instanceof Request) {
                request = (Request) object;
            }
            return request;
        }

        private Request handleConnection(Request request) {

            serverHandler.setRequest(request);
            serverHandler.setUsersMap(usersMap);
            serverHandler.setServerEngine(serverEngine);

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
    }
}
