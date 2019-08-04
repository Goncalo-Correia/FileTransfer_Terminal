package org.musicsource.codezillas.server.persistence.models;

import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table(name = "user")
public class User extends AbstractModel {

    private String username;

    //@OneToMany(
    //        cascade = {CascadeType.ALL},
    //        orphanRemoval = true,
    //        mappedBy = "user",
    //        fetch = FetchType.EAGER
    //)
    private List<Track> trackList = new ArrayList<>();

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Track> getTrackList() {
        return trackList;
    }

    public void setTrackList(List<Track> trackList) {
        this.trackList = trackList;
    }
}
