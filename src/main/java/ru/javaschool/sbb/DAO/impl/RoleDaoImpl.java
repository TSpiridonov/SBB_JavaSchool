package ru.javaschool.sbb.DAO.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.sbb.DAO.api.RoleDAO;
import ru.javaschool.sbb.entity.Role;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDaoImpl implements RoleDAO {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Role findRoleByTitle(String title) {
        Role role;
        try {
            role = (Role) entityManager.createQuery("SELECT r FROM Role r where r.title = :title")
                    .setParameter("title", title).getSingleResult();
        } catch (NoResultException e) {
            role = null;
        }
        return role;
    }

    @Override
    public Role getRoleById(int id) {
        return entityManager.find(Role.class, id);
    }
}
