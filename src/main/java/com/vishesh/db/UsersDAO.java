package com.vishesh.db;

import com.google.inject.Inject;
import com.vishesh.core.StoredUser;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import java.util.Optional;

public class UsersDAO extends AbstractDAO<StoredUser> {

    @Inject
    public UsersDAO(final SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public StoredUser createUser(final StoredUser storedUser) {
        return persist(storedUser);
    }

    public Optional<StoredUser> getUser(final String userId) {
        return Optional.ofNullable(get(userId));
    }

    public Optional<StoredUser> findByEmail(String email) {
        Query<StoredUser> query = namedTypedQuery("StoredUser.findByEmail");
        query.setParameter("email", email);
        return Optional.ofNullable(query.uniqueResult());
    }
}
