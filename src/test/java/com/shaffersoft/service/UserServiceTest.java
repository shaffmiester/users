package com.shaffersoft.service;

import com.shaffersoft.User;
import com.shaffersoft.dao.UserDAO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock UserDAO userDAO;
    @InjectMocks private UserService userService;

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
        when(userDAO.getUsers()).thenReturn(users);

        List<User> actualUsers = userService.getAllUsers();

        verify(userDAO).getUsers();
        assertSame(users, actualUsers);
    }

}