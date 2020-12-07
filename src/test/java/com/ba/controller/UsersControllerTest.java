package com.ba.controller;

import com.ba.dto.AuthoritiesDTO;
import com.ba.dto.UsersDTO;
import com.ba.entity.Users;
import com.ba.service.UsersService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UsersControllerTest {

    @InjectMocks
    private UsersController controller;

    @Mock
    private UsersService service;

    private Users users = new Users();
    private UsersDTO usersDTO = new UsersDTO();
    private List<Users> usersList = new ArrayList<>();
    private List<UsersDTO> usersListDTO = new ArrayList<>();
    private String username;

    @Before
    public void setUp() throws Exception {
        users.setEnabled(true);
        users.setPassword("123");
        users.setUsername("omer");

        usersDTO.setUsername("omerDTO");
        usersDTO.setPassword("123123");
        usersDTO.setEnabled(true);

        usersList.add(users);
        usersListDTO.add(usersDTO);
        username = "omer";
    }

    @Test
    public void shouldVerifyListAll() {
        Mockito.when(service.listUsers()).thenReturn(usersListDTO);
        List<UsersDTO> tempDTOList = controller.listUsers();

        assertEquals(tempDTOList,usersListDTO);
    }

    @Test
    public void shouldVerifyAddUsers() {
        when(service.addUsers(usersDTO)).thenReturn("User Added");
        String res = controller.addUsers(usersDTO);

        assertEquals(res,"User Added");
    }

    @Test
    public void shouldVerifyDeleteUsers() {

        when(service.deleteUsers(username)).thenReturn("User Deleted");
        String res = controller.deleteUsers(username);

        assertEquals(res,"User Deleted");
    }

    @Test
    public void shouldVerifyUpdateUsers() {
        when(service.updateUsers(usersDTO)).thenReturn(usersDTO);
        UsersDTO tempUsersDTO = controller.updateUsers(usersDTO);
        assertEquals(tempUsersDTO,usersDTO);
    }
}