package org.musicsource.codezillas.connection;

import org.musicsource.codezillas.server.persistence.tracks.Track;

import java.io.Serializable;

public class Connection implements Serializable {

    private ConnectionType connectionType;
    private Track track;

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
}
