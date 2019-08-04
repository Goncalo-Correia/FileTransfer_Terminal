package org.musicsource.codezillas.server.persistence;

import org.musicsource.codezillas.server.persistence.models.Track;
import org.musicsource.codezillas.server.persistence.models.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Store {

    private Map<User, Track> storeMap;

    public Store() {
        storeMap = Collections.synchronizedMap(new HashMap<User, Track>());
    }


    public Map<User, Track> getStoreMap() {
        return storeMap;
    }


}
