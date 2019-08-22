package org.musicsource.codezillas.client;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;
import org.musicsource.codezillas.connection.Request;

import java.net.Socket;

public class ClientConnection {

    private Prompt prompt;
    private ClientRequest clientRequest;
    private ClientFileManager clientFileManager;

    public ClientConnection() {
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
                if (selectedFile == 1) {
                    return clientRequest.backToMainRequest();
                }

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

    public Request backToMainConnection() {
        return clientRequest.backToMainRequest();
    }

    private Integer createMenu(String[] files) {
        MenuInputScanner menuScanner = new MenuInputScanner(files);
        menuScanner.setMessage("Client personal files: ");
        return prompt.getUserInput(menuScanner);
    }

    private String bootFileManager() {
        StringInputScanner strScanner = new StringInputScanner();
        strScanner.setMessage("Enter PC user:");
        return prompt.getUserInput(strScanner);
    }

    public void setClientFileManager(ClientFileManager clientFileManager) {
        this.clientFileManager = clientFileManager;
    }

    public void setClientRequest(ClientRequest clientRequest) {
        this.clientRequest = clientRequest;
    }
}
