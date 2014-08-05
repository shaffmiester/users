package com.shaffersoft.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shaffersoft.User;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class UserDAO {

    private ObjectMapper mapper = new ObjectMapper();
    private InputStream data;
    private List<User> users;

    public UserDAO(){
        users = initializeUsers();
    }

    public UserDAO(List<User> users){
        this.users = users;
    }

    public User getUser(String id){
        return findUserById(id);
    }


    private List<User> initializeUsers() {
        List<User> users = new ArrayList<User>();
        try {
            getFile();
            users = mapper.readValue(data, new TypeReference<List<User>>() {} );
            return users;

        } catch (IOException e) {
            throw new RuntimeException("Error processing data file ", e);
        }
    }

    public List<User> getUsers() {
       return users;
    }

    public void addUser(String name, String email, List<String> descriptions){
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setId(UUID.randomUUID().toString());
        user.setDescriptions(descriptions);
        users.add(user);
    }

    private void getFile(){
        data = this.getClass().getResourceAsStream("/com/shaffersoft/sample_data.json");
    }


    public void deleteUser(String id) {
        for(Iterator<User> itr = users.listIterator(); itr.hasNext(); ){
            User user = itr.next();
            if(StringUtils.equalsIgnoreCase(id,user.getId())){
                itr.remove();
                return;
            }
        }
    }

    public User editUser(String id, String name, String email, List<String> descriptions) {
        User user = findUserById(id);
        user.setName(name);
        user.setEmail(email);
        user.setDescriptions(descriptions);
        return user;
    }

    private User findUserById(String id){
        for(User currUser : users){
            if(StringUtils.equalsIgnoreCase(id, currUser.getId())){
                return currUser;
            }
        }
        return null;
    }

    public List<User> getFilteredUsers(List<String> descriptions) {
        List<User> filteredUsers = new ArrayList<User>();
        for(User currUser : users){
            if(currUser.getDescriptions() != null) {
                for(String description : descriptions) {
                    if (currUser.getDescriptions().contains(description.toLowerCase())) {
                        filteredUsers.add(currUser);
                        break;
                    }
                }
            }
        }
        return filteredUsers;
    }
}
