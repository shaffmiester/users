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
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
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
        userTwo.setEmail("jrs32491@ucmo.edu");
        userTwo.setName("Jared");
        userTwo.setId("2");

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

    @Test
    public void getFilteredUsersTest(){
        String description = "sad";
        when(userDAO.getFilteredUsers(anyString())).thenReturn(users);

        List<User> actualUsers = userService.getFilteredUsers(description);

        verify(userDAO).getFilteredUsers(eq(description));
        assertSame(users, actualUsers);
    }

    @Test
    public void deleteUserCallsUserDAODeleteUser(){
        userService.deleteUser("123");

        verify(userDAO).deleteUser("123");
    }

    @Test
    public void addUserCallsUserDAOAddTest(){
        String name = "Joel";
        String email = "joel@gmail.com";
        List<String> descriptions = new ArrayList<String>();

        userService.addUser(name, email, descriptions);

        verify(userDAO).addUser(eq(name), eq(email), eq(descriptions));
    }

    @Test
    public void editUserCallsUserDAOEditUser() {
        String id = "123";
        String name = "Joel";
        String email = "joel@gmail.com";
        List<String> descriptions = new ArrayList<String>();
        descriptions.add("Glad");

        userService.editUser(id, name, email, descriptions);

        verify(userDAO).editUser(id, name, email, descriptions);
    }

}