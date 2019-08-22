package org.musicsource.codezillas;

import org.musicsource.codezillas.server.Server;

public class ServerMain {

    public static void main(String[] args) {

        Server server = new Server();

        server.control();
        server.init();
        server.start();
        server.shutdown();
    }

}
