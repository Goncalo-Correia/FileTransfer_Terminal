package org.musicsource.codezillas.client;

import org.academiadecodigo.bootcamp.Prompt;
import org.musicsource.codezillas.connection.Request;
import org.musicsource.codezillas.connection.RequestType;
import org.musicsource.codezillas.connection.commands.Command;
import org.musicsource.codezillas.connection.commands.CommandType;

public class ClientEngine {

    private Prompt prompt;

    public ClientEngine() {
    }

    public void setPrompt(Prompt prompt) {
        this.prompt = prompt;
    }

    public Request initConnection(Integer option){
        Request request = new Request();
       switch (option) {
           case 1:
               request.setRequestType(RequestType.COMMAND);
               Command command = new Command();
               command.setCommandType(CommandType.LOGIN);
               request.setCommand(command);
               break;
           case 2:
               request.setRequestType(RequestType.COMMAND);
               Command command1 = new Command();
               command1.setCommandType(CommandType.REGISTER);
               request.setCommand(command1);
               break;
           case 3:
               System.exit(0);
               break;
       }
       return request;
    }

    public Request validateConnection(String[] client) {
        Request request = new Request();
        request.setRequestType(RequestType.COMMAND);
        Command command = new Command();
        command.setCommandType(CommandType.CREDENTIALS);
        command.setMenuOptions(client);
        request.setCommand(command);
        return request;
    }

    public Request mainConnection(Integer mainOption) {
        Request request = null;
        switch (mainOption) {
            case 1:
                request.setRequestType(RequestType.COMMAND);
                Command command = new Command();
                command.setCommandType(CommandType.UPDATE);
                request.setCommand(command);
                break;
            case 2:
                request.setRequestType(RequestType.COMMAND);

                Command command1 = new Command();
                command1.setCommandType(CommandType.UPLOAD);
                request.setCommand(command1);
                break;
            case 3:
                request.setRequestType(RequestType.COMMAND);
                Command command2 = new Command();
                command2.setCommandType(CommandType.DOWNLOAD);
                request.setCommand(command2);
                break;
            case 4:
                request.setRequestType(RequestType.COMMAND);
                Command command3 = new Command();
                command3.setCommandType(CommandType.QUIT);
                request.setCommand(command3);
                break;
        }
        return request;
    }

    public Request newUserConnection(String[] registerOptions) {
        return validateConnection(registerOptions);
    }

}
