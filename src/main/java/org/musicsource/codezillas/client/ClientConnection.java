package org.musicsource.codezillas.client;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.musicsource.codezillas.connection.Request;

import java.net.Socket;

public class ClientConnection {

    private Prompt prompt;
    private ClientRequest clientRequest;
    private ClientFileManager clientFileManager;

    public ClientConnection() {
        clientRequest = new ClientRequest();
        clientFileManager = new ClientFileManager();
    }

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }

    public Request initConnection(Integer option){
        Request request = new Request();
       switch (option) {
           case 1:
               request = clientRequest.loginRequest();
               break;
           case 2:
               request = clientRequest.registerRequest();
               break;
           case 3:
               System.exit(0);
               break;
       }
       return request;
    }

    public Request validateConnection(String[] client) {
        return clientRequest.validateRequest(client);
    }

    public Request mainConnection(Integer mainOption) {
        Request request = null;
        switch (mainOption) {
            case 1:
                request = clientRequest.availableFiles();
                break;
            case 2:
                Integer selectedFile = createMenu(clientFileManager.listClientFilesForFolder());
                byte[] files = clientFileManager.uploadFile(selectedFile);
                String fileName = clientFileManager.getFileName();
                request = clientRequest.uploadRequest(files, fileName);
                break;
            case 3:
                request = clientRequest.serverFilesRequest();
                break;
            case 4:
                request = clientRequest.quitRequest();
                break;
        }
        return request;
    }

    public Request updateConnection() {
        return clientRequest.backToMainRequest();
    }

    public Request serverFilesConnection(String fileName) {
        return clientRequest.downloadRequest(fileName);
    }

    public Request downloadRequest(byte[] fileData, String fileName) {
        clientFileManager.downloadFile(fileData, fileName);
        return clientRequest.backToMainRequest();
    }

    public Request newUserConnection(String[] registerOptions) {
        return clientRequest.newUserRequest(registerOptions);
    }

    private Integer createMenu(String[] files) {
        MenuInputScanner menuScanner = new MenuInputScanner(files);
        menuScanner.setMessage("Client personal files: ");
        return prompt.getUserInput(menuScanner);
    }

}
