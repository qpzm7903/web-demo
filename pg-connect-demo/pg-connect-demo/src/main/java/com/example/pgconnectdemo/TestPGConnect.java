package com.example.pgconnectdemo;

import java.sql.*;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * todo description
 *
 * @author qpzm7903
 * @since 2023-03-05-7:04
 */
public class TestPGConnect {
    public static void main(String[] args) throws SQLException, InterruptedException {

        final ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 100; i++) {
            Runnable runnable = () -> {
                try {
                    extracted();
                } catch (SQLException | InterruptedException e) {
                    System.out.println(e);
                }
            };
            executorService.execute(runnable);
        }



    }

    private static void extracted() throws SQLException, InterruptedException {
        String url = "jdbc:postgresql://localhost:5432/postgres";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "mysecretpassword");


        while (true){
            Connection conn = DriverManager.getConnection(url, props);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from student");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
//                System.out.println(id);
//                System.out.println(name);
            }
            statement.close();
            conn.close();

            Thread.sleep(100);
        }
    }
}
