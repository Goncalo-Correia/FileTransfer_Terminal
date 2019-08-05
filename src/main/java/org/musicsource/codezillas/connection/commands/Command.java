package org.musicsource.codezillas.connection.commands;

import java.io.Serializable;

public class Command implements Serializable {

    private CommandType commandType;
    private String message;
    private String[] menuOptions;

    public Command() {
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String[] getMenuOptions() {
        return menuOptions;
    }

    public void setMenuOptions(String[] menuOptions) {
        this.menuOptions = menuOptions;
    }
}
