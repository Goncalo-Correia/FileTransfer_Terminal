package org.musicsource.codezillas.server.persistence.daos.daoImpl;

import org.musicsource.codezillas.server.persistence.models.Track;

public class TrackDao extends GenericDao<Track> implements org.musicsource.codezillas.server.persistence.daos.TrackDao {

    public TrackDao() {
        super(Track.class);
    }
}
