package org.musicsource.codezillas.connection;

import org.musicsource.codezillas.connection.commands.Command;
import org.musicsource.codezillas.server.persistence.models.Track;

import java.io.Serializable;

public class Connection implements Serializable {

    private ConnectionType connectionType;
    private Track track;
    private Command command;

    public Connection() {
    }

    public ConnectionType getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(ConnectionType connectionType) {
        this.connectionType = connectionType;
    }

    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
