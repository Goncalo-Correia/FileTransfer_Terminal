package org.musicsource.codezillas.server.persistence.daos.jpa;

import org.musicsource.codezillas.server.persistence.daos.UserDao;
import org.musicsource.codezillas.server.persistence.models.User;

public class JpaUserDao extends GenericJpaDao<User> implements UserDao {

    public JpaUserDao() {
        super(User.class);
    }
}
