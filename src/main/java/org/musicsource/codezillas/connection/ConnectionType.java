package org.musicsource.codezillas.connection;

import java.io.Serializable;

public enum ConnectionType implements Serializable {
    BOOT,
    COMMAND,
    UPLOAD,
    DOWNLOAD;

    ConnectionType() {
    }
}
