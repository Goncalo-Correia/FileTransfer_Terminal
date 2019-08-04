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

    public Server() {
        cachedPool = Executors.newCachedThreadPool();
        connectionHandlerMap = Collections.synchronizedMap(new HashMap<Integer, ConnectionHandler>());
        store = new Store();
        clientCount = 0;
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
                ConnectionHandler clientHandler = new ConnectionHandler(socket, store);

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

        public ConnectionHandler(Socket socket, Store store) {
            this.socket = socket;
            this.store = store;
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
            handle();
        }

        private void handle() {
            while (serverSocket.isBound()) {
                try {
                    Object object = inputStream.readObject();
                    handleConnection(object);

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        private void handleConnection(Object object) {

            if (object instanceof Connection) {

                Connection connection = (Connection) object;

                serverHandler.setConnection(connection);
                serverHandler.setStore(store);
                serverHandler.handleConnection();
            }
        }
    }
}
