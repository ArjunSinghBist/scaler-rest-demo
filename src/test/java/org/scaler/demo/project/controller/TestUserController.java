package org.scaler.demo.project.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.ZoneId;

@SpringBootTest
public class TestUserController {

    private UserController userController;

    @Autowired
    public TestUserController(UserController userController) {
        this.userController = userController;
    }

    @Test
    @DisplayName("Pass first and last Name, returns hello message with the name")
    public void Test_getName_returnsHelloMessageWithName() {
        String userName = "John Doe";
        String toName = "Nicky Doe";
        assert(userController.getName(userName, toName).equals("Hello! from " + userName + " to " + toName));
        assert(userController.getName(userName, "").equals("Hello! from " + userName + " to "));
    }

    @Test
    @DisplayName("Providing three input parameters returns string output")
    public void Test_getRequestParam_threeInputs_returnsString() {
        String name = "John Doe";
        String toName = "Nicky Doe";
        String createdAt = LocalDateTime.now().toString();
        String output = "Hello! from " + name + " to " + toName + " at " + LocalDateTime.parse(createdAt).atZone(ZoneId.systemDefault()).toString();

        assert(userController.getRequestParam(name, toName, createdAt).equals(output));
    }

    public void Test_getRequestParam_twoInputs_returnsString() {
        String name = "John Doe";
//        String toName = "Nicky Doe";
        String createdAt = LocalDateTime.now().toString();
        String output = "Hello! from " + name + " to   at " + LocalDateTime.parse(createdAt).atZone(ZoneId.systemDefault()).toString();


    }
}
