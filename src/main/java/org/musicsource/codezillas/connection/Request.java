package org.musicsource.codezillas.connection;

import org.musicsource.codezillas.connection.commands.Command;
import org.musicsource.codezillas.server.persistence.models.Track;
import org.musicsource.codezillas.server.persistence.models.User;

import java.io.Serializable;

public class Request implements Serializable {

    private RequestType requestType;
    private Track track;
    private Command command;

    public Request() {
    }

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
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
