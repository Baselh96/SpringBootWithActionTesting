package com.example.dori.service;

import com.example.dori.model.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserserviceTest {
    @Autowired
    Userservice userservice;

    @BeforeEach
    void setUp() {
    }

    @Nested
    class create{
        @Test
        @DisplayName("User with all Atributs")
        void createUserWithAllAttributs() throws Exception {
            User expectedUser = new User("test", "test@gmail.com", "123");
            Assertions.assertEquals(expectedUser, userservice.create(expectedUser));
        }

        @Test
        @DisplayName("User without all Atributs")
        void createUserWithoutAllAttributs() throws Exception {
            User expectedUser = new User(null, "test@gmail.com", "123");
            Assertions.assertThrows(Exception.class , () -> userservice.create(expectedUser));
        }
    }


    @Test
    void checkUser() {
        User expectedUser = new User(null, "test@gmail.com", "123");
        Assertions.assertThrows(Exception.class , () -> userservice.checkUser(expectedUser));
    }

    @Test
    void login() throws Exception {
        User expectedUser = new User("basel", "test@gmail.com", "123");
        userservice.create(expectedUser);
        Assertions.assertEquals(expectedUser, userservice.login(expectedUser));
    }

    @Test
    void getUser() {
    }

    @Test
    void userNotFind() {
    }

    @Test
    void getAllFriends() {
    }

    @Test
    void getFriend() {
    }

    @Test
    void isfriend() {
    }

    @Test
    void addFriend() {
    }
}