package org.musicsource.codezillas.connection;

import java.io.Serializable;

public enum ConnectionType implements Serializable {
    COMMAND,
    UPLOAD,
    DOWNLOAD;

    ConnectionType() {
    }
}
