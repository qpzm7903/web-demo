package com.example.pgconnectdemo;

import com.example.pgconnectdemo.dao.User;
import com.example.pgconnectdemo.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * todo description
 *
 * @author qpzm7903
 * @since 2023-03-05-23:09
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }


    @Service
    class Test {

        private final DataSource dataSource;
        private final UserService userService;

        Test(DataSource dataSource, UserService userService) {
            this.dataSource = dataSource;
            this.userService = userService;
        }

        @EventListener(classes = {ContextRefreshedEvent.class})
        public void handleMultipleEvents() {


            final ExecutorService executorService = Executors.newFixedThreadPool(100);
            for (int i = 0; i < 100; i++) {
                Runnable runnable = () -> {
                    while (true) {
                        userService.findAll();
//                        System.out.println(all);

                        User user1 = new User();
                        Random random = new Random();
                        user1.setName("test" + random.nextLong());
                        userService.createUser(user1);
                        user1 = null;

                        userService.findAll();
//                        System.out.println(all);
                    }
                };
                executorService.execute(runnable);
            }




        }

    }
}
