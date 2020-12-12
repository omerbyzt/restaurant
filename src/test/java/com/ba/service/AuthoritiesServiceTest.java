//package com.ba.service;
//
//import com.ba.builder.AuthoritiesBuilder;
//import com.ba.builder.AuthoritiesDTOBuilder;
//import com.ba.dto.AuthoritiesDTO;
//import com.ba.entity.Authorities;
//import com.ba.repository.AuthoritiesRepository;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import java.util.List;
//
//import static org.mockito.Matchers.any;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.internal.verification.VerificationModeFactory.times;
//import static org.mockito.Mockito.verify;
//
//@RunWith(MockitoJUnitRunner.class)
//public class AuthoritiesServiceTest {
//
//    @InjectMocks
//    private AuthoritiesService authoritiesService;
//
//    @Mock
//    private AuthoritiesRepository authoritiesRepository;
//
//    private Authorities auth = new Authorities();
//    private AuthoritiesDTO authDTO = new AuthoritiesDTO();
//    private String username = "omer";
//
//    @Before
//    public void setUp() throws Exception {
//        auth = new AuthoritiesBuilder().username("omer").authority("ROLE_USER").build();
//
//        authDTO = new AuthoritiesDTOBuilder().username("omerDTO").authority("ROLE_USER").build();
//    }
//
//    @Test
//    public void shouldAddNewAuth() {
//        Mockito.when(authoritiesRepository.save(any())).thenReturn(auth);
//        String res = authoritiesService.addAuth(authDTO);
//        assertNotNull(res);
//        assertEquals(res, "Auth Added");
//    }
//
//    @Test
//    public void shouldDeleteAuth() {
//        String res = authoritiesService.deleteAuth(username);
//        assertEquals(res, "Auth Deleted");
//        verify(authoritiesRepository, times(1)).deleteById(username);
//    }
//
//
//    @Test(expected = RuntimeException.class)
//    public void shouldNotDeleteAuthWhenThrownException() {
//        doThrow(new RuntimeException("Cant delete here")).when(authoritiesRepository).deleteById(username);
//        String res = authoritiesService.deleteAuth(username);
//    }
//
//    @Test
//    public void shouldUpdateAuth() {
//        Mockito.when(authoritiesRepository.saveAndFlush(auth)).thenReturn(auth);
//        AuthoritiesDTO authDTO2 = authoritiesService.updateAuth(authDTO);
//        assertNotNull(authDTO2);
//        assertEquals(authDTO2, authDTO);
//    }
//
//    @Test
//    public void shouldListAuth() {
//        List<AuthoritiesDTO> authList = authoritiesService.authoritiesList();
//        assertNotNull(authList);
//    }
//}