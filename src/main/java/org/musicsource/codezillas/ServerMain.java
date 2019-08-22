package org.musicsource.codezillas;

import org.musicsource.codezillas.server.Server;

public class ServerMain {

    public static void main(String[] args) {

        Server server = new Server();

        server.init();
        server.control();
        server.start();
        server.shutdown();
    }

}
