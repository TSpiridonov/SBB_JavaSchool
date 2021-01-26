package ru.javaschool.sbb.DAO.api;


import ru.javaschool.sbb.entity.Role;

public interface RoleDAO {

    Role findRoleByTitle(String title);

    Role getRoleById(int id);
}
