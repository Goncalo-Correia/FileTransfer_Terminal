package org.musicsource.codezillas.server;

import org.musicsource.codezillas.server.persistence.models.Track;
import org.musicsource.codezillas.server.persistence.models.User;
import org.musicsource.codezillas.server.services.TrackService;
import org.musicsource.codezillas.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ServerServices {

    private UserService userService;
    private TrackService trackService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setTrackService(TrackService trackService) {
        this.trackService = trackService;
    }

    public User getUserById(Integer id) {
        return userService.getById(id);
    }

    public User saveUser(User user) {
        return userService.saveUser(user);
    }

    public void deleteUser(Integer id) {
        userService.delete(id);
    }

    public List<User> getUsersList() {
        return userService.listUsers();
    }

    public Track getTrackById(Integer id) {
        return trackService.getById(id);
    }

    public Track saveTrack(Track track) {
        return trackService.saveTrack(track);
    }

    public void deleteTrack(Integer id) {
        trackService.delete(id);
    }

    public List<Track> getTracksList() {
        return trackService.listTracks();
    }

}
