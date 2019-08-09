package org.musicsource.codezillas.server.services;

import org.musicsource.codezillas.server.persistence.models.User;

import java.util.List;

public interface UserService {

    User getById(Integer id);
    User saveUser(User user);
    void delete(Integer id);
    List<User> listUsers();
    /*List<User> listUserTracks(Integer id);
    void addTrack(String trackName, String trackPath);
    void removeTrack(Integer id);*/

}
