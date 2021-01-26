package ru.javaschool.sbb.DAO.api;


import ru.javaschool.sbb.entity.User;

public interface UserDAO {

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    void addUser(User user);
}