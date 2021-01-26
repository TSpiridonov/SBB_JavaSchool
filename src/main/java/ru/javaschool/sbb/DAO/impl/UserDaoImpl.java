package ru.javaschool.sbb.DAO.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.sbb.DAO.api.UserDAO;
import ru.javaschool.sbb.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class UserDaoImpl implements UserDAO {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findUserByUsername(String username) {
        User user;
        try {
            user = (User) entityManager.createQuery("SELECT u FROM User u where u.username = :username")
                    .setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            user = null;
        }
        return user;
    }

    @Override
    public User findUserByEmail(String email) {
        User user;
        try {
            user = (User) entityManager.createQuery("SELECT u FROM User u where u.email = :email")
                    .setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
            user = null;
        }
        return user;
    }

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }
}
