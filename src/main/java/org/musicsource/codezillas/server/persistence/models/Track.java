package org.musicsource.codezillas.server.persistence.models;

import javax.persistence.*;

@Entity
@Table(name = "track")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Track extends AbstractModel {

    private byte[] trackData;
    private String fileName;

    @ManyToOne
    private User user;

    public Track() {
    }

    public byte[] getTrackData() {
        return trackData;
    }

    public void setTrackData(byte[] trackData) {
        this.trackData = trackData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
