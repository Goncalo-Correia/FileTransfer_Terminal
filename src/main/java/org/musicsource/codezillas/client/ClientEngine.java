package org.musicsource.codezillas.client;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.musicsource.codezillas.connection.Request;
import org.musicsource.codezillas.connection.RequestType;
import org.musicsource.codezillas.connection.commands.Command;
import org.musicsource.codezillas.connection.commands.CommandType;

public class ClientEngine {

    private Prompt prompt;
    private ClientRequest clientRequest;
    private ClientFileManager clientFileManager;

    public ClientEngine() {
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
                request = clientRequest.updateRequest();
                break;
            case 2:
                Integer selectedFile = createMenu(clientFileManager.listClientFilesForFolder());
                String[] fileInfo = clientFileManager.clientFile(selectedFile);
                request = clientRequest.uploadRequest(fileInfo);
                break;
            case 3:
                request = clientRequest.downloadRequest();
                break;
            case 4:
                request = clientRequest.quitRequest();
                break;
        }
        return request;
    }

    public Request newUserConnection(String[] registerOptions) {
        Request request = new Request();
        request.setRequestType(RequestType.COMMAND);
        Command command = new Command();
        command.setCommandType(CommandType.ADD_USER);
        command.setMenuOptions(registerOptions);
        request.setCommand(command);
        return request;
    }

    private Integer createMenu(String[] files) {
        MenuInputScanner menuScanner = new MenuInputScanner(files);
        menuScanner.setMessage("Client personal files: ");
        return prompt.getUserInput(menuScanner);
    }

}
