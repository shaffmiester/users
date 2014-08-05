package com.shaffersoft.dao;

import com.shaffersoft.User;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class UserDAOTest extends TestCase {


    private UserDAO userDAO;
    private List<User> users;
    User userOne;
    User userTwo;

    @Before
    public void setUp(){
        List<String> descriptions = new ArrayList<String>();
        descriptions.add("happy");

        userOne = new User();
        userOne.setEmail("jrs32490@ucmo.edu");
        userOne.setName("Joel");
        userOne.setId("1");
        userOne.setDescriptions(descriptions);

        userTwo = new User();
        userTwo.setEmail("jrs32491@ucmo.edu");
        userTwo.setName("Jared");
        userTwo.setId("2");
        userTwo.setDescriptions(new ArrayList<String>());

        users = new ArrayList<User>();
        users.add(userOne);
        users.add(userTwo);

        userDAO = new UserDAO(users);
    }

    @Test
    public void testGetUser() throws Exception {
        User actualUser = userDAO.getUser("1");

        assertEquals(userOne, actualUser);

    }

    @Test
    public void testGetUsers() throws Exception {
        List<User> actualUsers = userDAO.getUsers();

        assertEquals(2, users.size());
        assertEquals(userOne, users.get(0));
        assertEquals(userTwo, users.get(1));
    }

    @Test
    public void testAddUser() throws Exception {
        String name = "Jon";
        String email = "jon@yahoo.com";
        List<String> descriptions = new ArrayList<String>();

        userDAO.addUser(name, email, descriptions);

        assertEquals(3, userDAO.getUsers().size());

    }

    @Test
    public void testDeleteUser() throws Exception {
        userDAO.deleteUser("1");

        List<User> retrievedUsers = userDAO.getUsers();

        assertEquals(1, retrievedUsers.size());
        assertEquals(userTwo, retrievedUsers.get(0));
    }

    @Test
    public void testEditUser() throws Exception {
        String name = "Jon";
        String email = "jon@yahoo.com";
        List<String> descriptions = new ArrayList<String>();

        User editedUser = userDAO.editUser("1", name, email, descriptions);

        assertEquals("Jon", editedUser.getName());
        assertEquals("jon@yahoo.com", editedUser.getEmail());
        assertEquals(descriptions, editedUser.getDescriptions());

    }

    @Test
    public void testGetFilteredUsers() throws Exception {

        List<String> descriptions = new ArrayList<String>();
        descriptions.add("happy");
        List<User> filteredUsers = userDAO.getFilteredUsers(descriptions);

        assertEquals(1, filteredUsers.size());
        assertEquals(userOne, filteredUsers.get(0));
    }
}