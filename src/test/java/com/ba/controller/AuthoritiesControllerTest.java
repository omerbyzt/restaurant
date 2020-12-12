//package com.ba.controller;
//
//import com.ba.builder.AuthoritiesBuilder;
//import com.ba.builder.AuthoritiesDTOBuilder;
//import com.ba.dto.AuthoritiesDTO;
//import com.ba.entity.Authorities;
//import com.ba.service.AuthoritiesService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//
//@RunWith(MockitoJUnitRunner.class)
//public class AuthoritiesControllerTest {
//
//    @InjectMocks
//    private AuthoritiesController controller;
//
//    @Mock
//    private AuthoritiesService service;
//
//    private Authorities auth = new Authorities();
//    private AuthoritiesDTO authDTO = new AuthoritiesDTO();
//    private List<Authorities> authList = new ArrayList();
//    private List<AuthoritiesDTO> authListDTO = new ArrayList();
//
//    @Before
//    public void setUp() throws Exception {
//
//        auth = new AuthoritiesBuilder().username("omer").authority("ROLE_USER").build();
//
//        authDTO = new AuthoritiesDTOBuilder().username("omerDTO").authority("ROLE_USER").build();
//
//        authList.add(auth);
//        authListDTO.add(authDTO);
//    }
//
//    @Test
//    public void shouldVerifyListAuths() {
//        Mockito.when(service.authoritiesList()).thenReturn(authListDTO);
//        List<AuthoritiesDTO> tempDTOList = controller.authoritiesList();
//
//        assertEquals(tempDTOList, authListDTO);
//    }
//
//    @Test
//    public void shouldVerifyAddAuth() {
//        when(service.addAuth(authDTO)).thenReturn("Auth Added");
//        String res = controller.addAuth(authDTO);
//        assertEquals(res, "Auth Added");
//    }
//
//    @Test
//    public void shouldVerifyDeleteAuth() {
//        String username = "omer";
//        when(service.deleteAuth(username)).thenReturn("Auth Deleted");
//        String res = controller.deleteAuth(username);
//        assertEquals(res, "Auth Deleted");
//    }
//
//    @Test
//    public void shouldVerifyUpdateAuth() {
//        when(service.updateAuth(authDTO)).thenReturn(authDTO);
//        AuthoritiesDTO tempAuthDTO = controller.updateAuth(authDTO);
//        assertEquals(tempAuthDTO, authDTO);
//    }
//}