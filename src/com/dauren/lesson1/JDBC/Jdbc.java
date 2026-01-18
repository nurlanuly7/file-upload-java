package com.dauren.lesson1.JDBC;

import java.sql.*;

public class Jdbc {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "username";
        String password = "password";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {

            Statement stmt = conn.createStatement();
//
//            while (rs.next()) {
//                System.out.println(rs.getString("name"));
//            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void query(String query){

    }
}
