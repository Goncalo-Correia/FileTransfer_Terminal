package org.musicsource.codezillas.connection;

import java.io.Serializable;

public enum RequestType implements Serializable {
    BOOT,
    COMMAND,
    UPLOAD,
    DOWNLOAD;

    RequestType() {
    }
}
