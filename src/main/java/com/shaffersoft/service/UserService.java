package com.shaffersoft.service;

import com.shaffersoft.User;
import com.shaffersoft.dao.UserDAO;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    UserDAO userDAO = new UserDAO();

    public List<User> getAllUsers(){
        return userDAO.getUsers();
    }

    public User getUser(String id){
        return userDAO.getUser(id);
    }

    private List<User> createUsers(){
        List<User> users = new ArrayList<User>();
        return users;
    }

    public void addUser(String name, String email, List<String> descriptions) {
        userDAO.addUser(name, email, descriptions);
    }

    public List<String> getAllowableDescription(){
        List<String> allowableDescritpions = new ArrayList<String>();
        allowableDescritpions.add("happy");
        allowableDescritpions.add("mad");
        allowableDescritpions.add("glad");
        allowableDescritpions.add("sad");
        return allowableDescritpions;
    }

    public void deleteUser(String id) {
        userDAO.deleteUser(id);
    }

    public User editUser(String id, String name, String email, List<String> descriptions) {
        return userDAO.editUser(id, name, email, descriptions);
    }

    public List<User> getFilteredUsers(String description) {
        return userDAO.getFilteredUsers(description);
    }
}
