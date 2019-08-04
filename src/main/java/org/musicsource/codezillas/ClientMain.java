package org.musicsource.codezillas;

import org.academiadecodigo.bootcamp.Prompt;
import org.musicsource.codezillas.client.Client;

public class ClientMain {

    public static void main(String[] args) {
        Prompt prompt = new Prompt(System.in, System.out);

        Client client = new Client(prompt);

        client.init();
        client.start();
        client.shutdown();
    }

}
