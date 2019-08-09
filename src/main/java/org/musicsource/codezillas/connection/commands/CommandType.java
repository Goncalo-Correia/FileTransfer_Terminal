package org.musicsource.codezillas.connection.commands;

import java.io.Serializable;

public enum CommandType implements Serializable {
    INIT,
    LOGIN,
    VALIDATE,
    CREDENTIALS,
    REGISTER,
    NEW_USER,
    ADD_USER,
    CONFIRM,
    MAIN,
    UPDATE,
    UPLOAD,
    DOWNLOAD,
    QUIT,
    REBOOT;

    CommandType() {
    }
}
