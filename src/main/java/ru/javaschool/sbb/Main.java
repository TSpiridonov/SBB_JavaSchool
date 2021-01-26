package ru.javaschool.sbb;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/sbb?useSSL=false&serverTimezone=Europe/Moscow&allowPublicKeyRetrieval=true";
        String username = "root";
        String password = "lxcc2mk5";
        System.out.println("Connecting...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Connection successful!");
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}
