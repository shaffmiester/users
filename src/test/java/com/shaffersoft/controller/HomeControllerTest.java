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
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HomeControllerTest {

    @Mock Model model;
    @Mock UserService userService;
    @InjectMocks HomeController controller;

    private List<User> users;
    private List<String> descriptions;
    private User userOne;

    @Before
    public void setUp(){
        userOne = new User();
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

        descriptions = new ArrayList<String>();
        descriptions.add("Happy");
        descriptions.add("Mad");
        descriptions.add("Glad");
        descriptions.add("Sad");


    }

    @Test
    public void getAllUsersTest(){
        when(userService.getAllUsers()).thenReturn(users);
        when(userService.getAllowableDescription()).thenReturn(descriptions);

        String view = controller.loadUsers(model, new ArrayList<String>());

        verify(userService).getAllUsers();
        verify(userService, never()).getFilteredUsers(anyListOf(String.class));

        verify(model).addAttribute(eq("users"), eq(users));
        verify(model).addAttribute(eq("descriptions"), eq(descriptions));
        assertEquals("users",view);
    }

    @Test
    public void getAllUsersFilterTest(){
        List<String> descriptions = new ArrayList<String>();
        descriptions.add("happy");
        when(userService.getFilteredUsers(anyListOf(String.class))).thenReturn(users);
        when(userService.getAllowableDescription()).thenReturn(descriptions);

        String view = controller.loadUsers(model, descriptions);

        verify(userService).getFilteredUsers(descriptions);
        verify(userService, never()).getAllUsers();
        verify(model).addAttribute(eq("users"), eq(users));
        verify(model).addAttribute(eq("descriptions"), eq(descriptions));
        assertEquals("users",view);
    }


    @Test
    public void editTest(){
        when(userService.getAllowableDescription()).thenReturn(descriptions);
        when(userService.getUser(anyString())).thenReturn(userOne);

        String id = "123";

        String view = controller.edit(model, id);

        assertEquals("editUser",view);
        verify(model).addAttribute(eq("descriptions"), eq(descriptions));
        verify(model).addAttribute(eq("user"),eq(userOne));
    }

    @Test
    public void editUserTest(){
        String id = "123";
        String name = "Jared";
        String email = "jared@yahoo.com";
        List<String> descriptions = new ArrayList<>();
        when(userService.getAllUsers()).thenReturn(users);

        String view = controller.editUser(model, id, name, email, descriptions);

        assertEquals("users",view);
        verify(userService).editUser(eq(id), eq(name), eq(email), eq(descriptions));
        verify(model).addAttribute(eq("descriptions"), eq(descriptions));
        verify(model).addAttribute(eq("users"), eq(users));
    }

    @Test
    public void deleteUserTest(){
        when(userService.getAllUsers()).thenReturn(users);
        when(userService.getAllowableDescription()).thenReturn(descriptions);
        String id = "123";

        String view = controller.deleteUser(model, id);

        assertEquals("users",view);
        verify(model).addAttribute(eq("users"), eq(users));
        verify(model).addAttribute(eq("descriptions"), eq(descriptions));

    }

    @Test
    public void loadUserTest(){
        String id = "123";
        String view = controller.loadUser(model, id);

        assertEquals("user",view);
        verify(model).addAttribute(eq("id"), eq(id));

    }

    @Test
    public void addUserTest(){
        when(userService.getAllUsers()).thenReturn(users);
        String name = "Jared";
        String email = "jared@yahoo.com";

        String view = controller.addUser(model, name, email, descriptions);

        verify(userService).addUser(eq(name), eq(email), eq(descriptions));
        verify(model).addAttribute(eq("users"), eq(users));
        assertEquals("users", view);
    }





}