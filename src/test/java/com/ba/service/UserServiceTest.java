package com.ba.service;

import com.ba.builder.RoleBuilder;
import com.ba.builder.RoleDTOBuilder;
import com.ba.builder.UserBuilder;
import com.ba.builder.UserDTOBuilder;
import com.ba.dto.RoleDTO;
import com.ba.dto.UserDTO;
import com.ba.entity.Role;
import com.ba.entity.User;
import com.ba.exception.BusinessRuleException;
import com.ba.exception.SystemException;
import com.ba.mapper.RoleMapper;
import com.ba.mapper.UserMapper;
import com.ba.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private RoleMapper roleMapper;

    private User user = new User();
    private UserDTO userDTO = new UserDTO();
    private List<User> userList = new ArrayList();
    private List<UserDTO> userDTOList = new ArrayList<>();
    private Long id = 111L;
    private Role role;
    private RoleDTO roleDTO;

    @Before
    public void setUp() throws Exception {
        user = new UserBuilder().id(id).email("testEmail").username("testUserName").password("testPassword").enabled(true).build();
        userDTO = new UserDTOBuilder().id(id).email("testDTOEmail").username("testDTOUserName").password("testDTOPassword").enabled(true).build();
        userList.add(user);
        userDTOList.add(userDTO);
        role = new RoleBuilder().id(1L).name("testRole").build();
        roleDTO = new RoleDTOBuilder().id(1L).name("testRoleDTO").build();
    }

    @Test
    public void shouldListUsers() {
        when(repository.findAll()).thenReturn(userList);
        when(userMapper.toDTOList(userList)).thenReturn(userDTOList);

        List<UserDTO> result = service.listUsers();

        assertNotNull(result);
        assertEquals(result,userDTOList);
    }

    @Test(expected = SystemException.class)
    public void shouldThrowSysExceptionWhenUserNullListUsers() {
        when(repository.findAll()).thenReturn(null);
        service.listUsers();
    }

    @Test
    public void shouldVerifyAddUser() {
        when(userMapper.toEntity(userDTO)).thenReturn(user);

        String res = service.addUser(userDTO);
        verify(repository).save(user);
        assertNotNull(res);
        assertEquals(res,"User Added");
    }

    @Test
    public void shouldDeleteUser() {
        String res = service.deleteUser(id);
        verify(repository).deleteById(id);

        assertNotNull(res);
        assertEquals(res,"User Deleted");
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldThrowBusRuleExceptionWhenDeleteUser() {
        doThrow(new BusinessRuleException("Id error")).when(repository).deleteById(id);
        service.deleteUser(id);
    }



    @Test
    public void shouldVerifyUpdateUser() {
        when(repository.findById(id)).thenReturn(Optional.of(user));
        when(roleMapper.toEntity(roleDTO)).thenReturn(role);

        String res = service.updateUser(userDTO);
        verify(repository).save(user);
        assertNotNull(res);
        assertEquals(res,"User Updated");
    }

    @Test(expected = SystemException.class)
    public void shouldThrowSysExceptionWhenUserNullUpdateUser() {
        when(repository.findById(id)).thenReturn(null);
        service.updateUser(userDTO);
    }
}