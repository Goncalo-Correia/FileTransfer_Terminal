package org.musicsource.codezillas.connection.commands;

import java.io.Serializable;

public enum CommandType implements Serializable {
    INIT,
    LOGIN,
    REGISTER,
    MAIN,
    UPDATE,
    UPLOAD,
    DOWNLOAD,
    QUIT,
    BOOT;

    CommandType() {
    }
}
