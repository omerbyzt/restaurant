package com.ba.service;

import com.ba.builder.UserBuilder;
import com.ba.builder.UserDTOBuilder;
import com.ba.dto.UserDTO;
import com.ba.entity.User;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper userMapper;

    private User user = new User();
    private UserDTO userDTO = new UserDTO();
    private List<User> userList = new ArrayList();
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
        when(repository.findAll()).thenReturn(userList);

        List<UserDTO> tempDTOList1 = userMapper.toDTOList(userList);
        List<UserDTO> tempDTOList2 = service.listUsers();

        assertEquals(tempDTOList1.get(0).getId(),tempDTOList2.get(0).getId());
    }

    @Test
    public void shouldVerifyAddUser() {
        when(repository.save(user)).thenReturn(user);
        String res = service.addUser(userDTO);

        assertNotNull(res);
        assertEquals(res,"User Added");
    }

    @Test(expected = RuntimeException.class)
    public void shouldVerifyDeleteUser() {
        doThrow(new RuntimeException("Cant delete here")).when(repository).deleteById(id);
        String res = service.deleteUser(id);
    }

    @Test
    public void shouldVerifyUpdateUser() {
        String res = service.updateUser(userDTO);
        assertNotNull(res);
        assertEquals(res,"User Updated");
    }
}