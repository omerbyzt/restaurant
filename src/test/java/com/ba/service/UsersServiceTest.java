package com.ba.service;

import com.ba.dto.UsersDTO;
import com.ba.entity.Users;
import com.ba.repository.UsersRepository;
import com.ba.unused.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Matchers.any;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UsersServiceTest {

    @InjectMocks
    private UsersService usersService;

    @Mock
    private UsersRepository usersRepository;

    private Users users = new Users();
    private UsersDTO usersDTO = new UsersDTO();

    @Before
    public void setUp() throws Exception {
        users.setEnabled(true);
        users.setPassword("123");
        users.setUsername("omer");

        usersDTO.setUsername("omerDTO");
        usersDTO.setPassword("123123");
        usersDTO.setEnabled(true);
    }

    @Test
    public void shouldAddNewUsers() {
        Mockito.when(usersRepository.save(any())).thenReturn(users);

        String res = usersService.addUsers(usersDTO);

        assertNotNull(res);
        assertEquals(res,"Users Added");
    }

    @Test
    public void shouldDeleteUsers() {
        String username = "omer";

        String res = usersService.deleteUsers(username);

        assertEquals(res,"Users Deleted");
        verify(usersRepository,times(1)).deleteById(username);
    }

    @Test(expected = RuntimeException.class)
    public void shouldNotDeleteAuthWhenThrownExeption() {
        String username = "omer";

        doThrow(new RuntimeException("Cant delete here")).when(usersRepository).deleteById(username);

        String res = usersService.deleteUsers(username);
    }

    @Test
    public void shouldUpdateUsers() {
        Mockito.when(usersRepository.saveAndFlush(users)).thenReturn(users);

        UsersDTO usersDTO2 = usersService.updateUsers(usersDTO);

        assertNotNull(usersDTO2);
        assertEquals(usersDTO2,usersDTO);
    }

    @Test
    public void shouldListUsers() {
        List<UsersDTO> usersDTOList = usersService.listUsers();
        assertNotNull(usersDTOList);
    }
}