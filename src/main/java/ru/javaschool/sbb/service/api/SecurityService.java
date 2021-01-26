package ru.javaschool.sbb.service.api;

public interface SecurityService {
    String findLoggedInUsername();
    void autoLogin(String username, String password);
}