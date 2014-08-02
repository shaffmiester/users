package com.shaffersoft.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shaffersoft.User;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserDAO {

    private ObjectMapper mapper = new ObjectMapper();
    private InputStream data;
    private List<User> users;

    public UserDAO(){
        users = initializeUsers();
    }

    public User getUser(String id){
        for(User currUser : users){
            if(StringUtils.equalsIgnoreCase(id, currUser.getId())){
                return currUser;
            }
        }
        return null;
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


}
