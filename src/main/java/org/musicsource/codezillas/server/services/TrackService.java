package org.musicsource.codezillas.server.services;

import org.musicsource.codezillas.server.persistence.models.Track;

import java.util.List;

public interface TrackService {

    Track getById(Integer id);
    Track saveTrack(Track track);
    void delete(Integer id);
    List<Track> listTracks();

}
