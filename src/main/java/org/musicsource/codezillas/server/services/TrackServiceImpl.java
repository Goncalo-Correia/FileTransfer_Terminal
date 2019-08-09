package org.musicsource.codezillas.server.services;

import org.musicsource.codezillas.server.persistence.daos.TrackDao;
import org.musicsource.codezillas.server.persistence.models.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class TrackServiceImpl implements TrackService {

    private TrackDao trackDao;

    @Autowired
    public void setTrackDao(TrackDao trackDao) {
        this.trackDao = trackDao;
    }

    @Override
    public Track getById(Integer id) {
        return trackDao.findById(id);
    }

    @Transactional
    @Override
    public Track saveTrack(Track track) {
        return trackDao.saveOrUpdate(track);
    }

    @Transactional
    @Override
    public void delete(Integer id) {
        trackDao.delete(id);
    }

    @Transactional
    @Override
    public List<Track> listTracks() {
        return trackDao.findAll();
    }
}
