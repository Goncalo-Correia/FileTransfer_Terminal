package org.musicsource.codezillas.server.persistence.daos.daoImpl;

import org.musicsource.codezillas.server.persistence.models.User;

public class UserDao extends GenericDao<User> implements org.musicsource.codezillas.server.persistence.daos.UserDao {

    public UserDao() {
        super(User.class);
    }
}
