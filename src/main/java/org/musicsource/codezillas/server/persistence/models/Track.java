package org.musicsource.codezillas.server.persistence.models;

import javax.persistence.*;

@Entity
@Table(name = "track")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Track extends AbstractModel {

    private String[] trackData;

    @ManyToOne
    private User user;

    public Track() {
    }

    public String[] getTrackData() {
        return trackData;
    }

    public void setTrackData(String[] trackData) {
        this.trackData = trackData;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
