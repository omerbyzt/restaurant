package com.ba.controller;

import com.ba.builder.RoleBuilder;
import com.ba.builder.RoleDTOBuilder;
import com.ba.dto.RoleDTO;
import com.ba.entity.Role;
import com.ba.exception.BusinessRuleException;
import com.ba.service.RoleService;
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
public class RoleControllerTest {

    @InjectMocks
    private RoleController controller;

    @Mock
    private RoleService service;

    private Role role = new Role();
    private RoleDTO roleDTO = new RoleDTO();
    private List<Role> roleList = new ArrayList<>();
    private List<RoleDTO> roleDTOList = new ArrayList<>();
    private Long id = 111L;

    @Before
    public void setUp() throws Exception {
        role = new RoleBuilder().id(111L).name("denemeRole").build();
        roleDTO = new RoleDTOBuilder().id(111L).name("denemeDTORole").build();
        roleList.add(role);
        roleDTOList.add(roleDTO);
    }

    @Test
    public void shouldVerifyListRoles() {
        Mockito.when(service.listRoles()).thenReturn(roleDTOList);
        List<RoleDTO> tempDTOList = controller.listRoles();

        assertEquals(tempDTOList,roleDTOList);
    }

    @Test
    public void shouldVerifyAddRole() {
        Mockito.when(service.addRole(roleDTO)).thenReturn("Role Added");
        String res = controller.addRole(roleDTO);

        assertEquals(res,"Role Added");
    }

    @Test
    public void shouldDeleteRole() {
        Mockito.when(service.deleteRole(id)).thenReturn("Role Deleted");
        String res = controller.deleteRole(id,"testString");

        assertNotNull(res);
        assertEquals(res,"Role Deleted");
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotDeleteRole() {
        Mockito.when(service.deleteRole(id)).thenReturn("Role Deleted");
        String res = controller.deleteRole((long) 0,"testString");
    }

    @Test
    public void shouldVerifyUpdateRole() {
        Mockito.when(service.updateRole(roleDTO)).thenReturn("Role Updated");
        String res = controller.updateRole(roleDTO);

        assertEquals(res,"Role Updated");
    }

    @Test(expected = BusinessRuleException.class)
    public void shouldNotUpdate() {
        roleDTO.setId(null);
        Mockito.when(service.updateRole(roleDTO)).thenReturn("Role Updated");
        String res = controller.updateRole(roleDTO);
    }
}