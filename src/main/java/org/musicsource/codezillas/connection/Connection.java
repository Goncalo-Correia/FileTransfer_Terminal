package org.musicsource.codezillas.connection;

import org.musicsource.codezillas.connection.commands.Command;
import org.musicsource.codezillas.server.persistence.models.Track;

import java.io.Serializable;

public class Connection implements Serializable {

    private ConnectionType connectionType;
    private Track track;
    private Command command;

}
