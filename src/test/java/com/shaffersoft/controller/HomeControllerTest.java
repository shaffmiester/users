package com.shaffersoft.controller;

import com.shaffersoft.User;
import com.shaffersoft.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

    @Mock Model model;
    @Mock UserService userService;
    @InjectMocks HomeController controller;

    private List<User> users;

    @Before
    public void setUp(){
        User userOne = new User();
        userOne.setEmail("jrs32490@ucmo.edu");
        userOne.setName("Joel");
        userOne.setId("1");

        User userTwo = new User();
        userOne.setEmail("jrs32491@ucmo.edu");
        userOne.setName("Jared");
        userOne.setId("2");

        users = new ArrayList<User>();
        users.add(userOne);
        users.add(userTwo);
    }

    @Test
    public void getAllUsersTest(){
        when(userService.getAllUsers()).thenReturn(users);

        String view = controller.loadUsers(model);

        verify(userService).getAllUsers();
        verify(model).addAttribute(eq("users"), eq(users));
        assertEquals("users",view);
    }



}