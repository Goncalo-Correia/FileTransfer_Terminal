package org.musicsource.codezillas.server;

import org.musicsource.codezillas.connection.Connection;
import org.musicsource.codezillas.server.persistence.Store;
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
    private Store store;
    private Integer clientCount;
    private ServerEngine serverEngine;

    public Server() {
        cachedPool = Executors.newCachedThreadPool();
        connectionHandlerMap = Collections.synchronizedMap(new HashMap<Integer, ConnectionHandler>());
        store = new Store();
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
                ConnectionHandler clientHandler = new ConnectionHandler(socket, store, serverEngine);

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
        private Store store;
        private ServerEngine serverEngine;

        public ConnectionHandler(Socket socket, Store store, ServerEngine serverEngine) {
            this.socket = socket;
            this.store = store;
            this.serverEngine = serverEngine;
            setupStreams();
            serverHandler = new ServerHandler();
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
                Connection connection = receiveConnection();

                Connection newConnection = handleConnection(connection);

                sendConnection(newConnection);
            }
        }

        private Connection receiveConnection() {
            Object object = null;
            Connection connection = null;

            try {
                //while (object == null) {
                    object = inputStream.readObject();
                //}
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            if (object instanceof Connection) {
                connection = (Connection) object;
            }
            return connection;
        }

        private Connection handleConnection(Connection connection) {

            serverHandler.setConnection(connection);
            serverHandler.setStore(store);
            serverHandler.setServerEngine(serverEngine);

            return serverHandler.handleConnection();
        }

        private void sendConnection(Connection connection) {

            try {
                outputStream.writeObject(connection);
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
