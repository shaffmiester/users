package com.shaffersoft.service;

import com.shaffersoft.dao.UserDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)

public class UserServiceTest {

    @Mock UserDAO userDAO;
    @InjectMocks private UserService userService;

    @Test
    public void test(){

    }

}