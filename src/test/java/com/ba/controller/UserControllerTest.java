package com.ba.controller;

import com.ba.builder.UserBuilder;
import com.ba.builder.UserDTOBuilder;
import com.ba.dto.UserDTO;
import com.ba.entity.User;
import com.ba.exception.BusinessRuleException;
import com.ba.service.UserService;
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

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController controller;

    @Mock
    private UserService service;

    private User user = new User();
    private UserDTO userDTO = new UserDTO();
    private List<User> userList = new ArrayList<>();
    private List<UserDTO> userDTOList = new ArrayList<>();
    private Long id = 111L;
    @Before
    public void setUp() throws Exception {
        user = new UserBuilder().id(id).email("testEmail").username("testUserName").password("testPassword").enabled(true).build();
        userDTO = new UserDTOBuilder().id(id).email("testDTOEmail").username("testDTOUserName").password("testDTOPassword").enabled(true).build();
        userList.add(user);
        userDTOList.add(userDTO);
    }

    @Test
    public void shouldVerifyListUsers() {
        Mockito.when(service.listUsers()).thenReturn(userDTOList);
        List<UserDTO> tempDTOList = controller.listUsers();

        assertEquals(tempDTOList,userDTOList);
    }

    @Test
    public void shouldVerifyAddUser() {
        Mockito.when(service.addUser(userDTO)).thenReturn("User Added");
        String res = controller.addUser(userDTO);

        assertEquals(res,"User Added");
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotAddUser() {
        Mockito.when(service.addUser(userDTO)).thenReturn("User Added");
        String res = controller.addUser(null);
    }

    @Test
    public void shouldVerifyDeleteUser() {
        Mockito.when(service.deleteUser(id)).thenReturn("User Deleted");
        String res = controller.deleteUser(id);

        assertEquals(res,"User Deleted");
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotDeleteUser() {
        Mockito.when(service.deleteUser(id)).thenReturn("User Deleted");
        String res = controller.deleteUser(null);
    }

    @Test
    public void shouldVerifyUpdateUser() {
        Mockito.when(service.updateUser(userDTO)).thenReturn("User Updated");
        String res = controller.updateUser(userDTO);

        assertEquals(res,"User Updated");
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotUpdateUser() {
        Mockito.when(service.updateUser(userDTO)).thenReturn("User Updated");
        String res = controller.updateUser(null);

    }
}