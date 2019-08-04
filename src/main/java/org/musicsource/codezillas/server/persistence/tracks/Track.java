package org.musicsource.codezillas.server.persistence.tracks;

import org.musicsource.codezillas.server.persistence.AbstractModel;
import org.musicsource.codezillas.server.persistence.User;

import javax.persistence.*;

//@Entity
//@Table(name = "track")
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Track extends AbstractModel {

    private String trackName;

    //@ManyToOne
    private User user;

    public Track() {
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
