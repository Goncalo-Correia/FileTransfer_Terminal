package org.musicsource.codezillas.server.persistence.daos.jpa;

import org.musicsource.codezillas.server.persistence.daos.TrackDao;
import org.musicsource.codezillas.server.persistence.models.Track;

public class JpaTrackDao extends GenericJpaDao<Track> implements TrackDao {

    public JpaTrackDao() {
        super(Track.class);
    }
}
