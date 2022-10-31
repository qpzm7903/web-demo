package com.example.springredissessiondemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;

/**
 * todo description
 *
 * @author qpzm7903
 * @since 2022-10-30-21:06
 */
@RestController
public class TestController {
    @Autowired
    private SessionRepository repository;

    @GetMapping("/test")
    String test(HttpServletRequest servletRequest,
                @RequestParam(value = "delete", required = false, defaultValue = "false") boolean delete) {
        HttpSession session = servletRequest.getSession();
        Object test = session.getAttribute("user");

//        String id = session.getId();
//        if (delete) {
//            Session byId = repository.findById(id);
//            assert byId != null;
//            repository.deleteById(id);
//            System.out.println("delete session " + id);
//            byId = repository.findById(id);
//            assert byId == null;
//
//        }
        return "test session id" + session.getId() + " user " + test;
    }

    @GetMapping("/delete")
    void delete(@RequestParam("sessionId") String sessionId){
        repository.deleteById(sessionId);
    }



}
