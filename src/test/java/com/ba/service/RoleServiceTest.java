package com.ba.service;

import com.ba.builder.RoleBuilder;
import com.ba.builder.RoleDTOBuilder;
import com.ba.dto.RoleDTO;
import com.ba.entity.Role;
import com.ba.exception.SystemException;
import com.ba.mapper.RoleMapper;
import com.ba.repository.RoleRepository;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class RoleServiceTest {

    @InjectMocks
    private RoleService service;

    @Mock
    private RoleRepository repository;

    @Mock
    private RoleMapper roleMapper;

    private Role role = new Role();
    private RoleDTO roleDTO = new RoleDTO();
    private List<Role> roleList = new ArrayList<>();
    private List<RoleDTO> roleDTOList = new ArrayList<>();
    private Long id = 111L;

    @Before
    public void setUp() throws Exception {
        role = new RoleBuilder().id(id).name("testName").build();
        roleDTO = new RoleDTOBuilder().id(id).name("testDTOName").build();
        roleList.add(role);
        roleDTOList.add(roleDTO);
    }

    @Test
    public void shouldListRoles() {
        when(repository.findAll()).thenReturn(roleList);
        when(roleMapper.toDTOList(roleList)).thenReturn(roleDTOList);

        List<RoleDTO> result = service.listRoles();
        assertNotNull(result);
        assertEquals(result,roleDTOList);
    }

    @Test(expected = SystemException.class)
    public void shouldThrowSysExceptionWhenRoleNullListRoles() {
        when(repository.findAll()).thenReturn(null);
        service.listRoles();
    }

    @Test
    public void shouldAddRole() {
        when(repository.save(role)).thenReturn(role);
        when(roleMapper.toEntity(roleDTO)).thenReturn(role);

        String res = service.addRole(roleDTO);

        assertNotNull(res);
        assertEquals(res,"Role Added");
    }

    @Test
    public void shouldDeleteRole() {
        String res = service.deleteRole(id);
        assertEquals(res,"Role Deleted");
        verify(repository,times(1)).deleteById(id);
    }

    @Test
    public void shouldUpdateRole() {
        when(repository.findById(roleDTO.getId())).thenReturn(Optional.of(role));
        when(roleMapper.toEntity(roleDTO)).thenReturn(role);

        String res = service.updateRole(roleDTO);
        verify(repository).saveAndFlush(role);
        assertNotNull(res);
        assertEquals(res,"Role Updated");
    }

    @Test(expected = SystemException.class)
    public void shouldThrowSysExceptionWhenRoleNullUpdateRole() {
        when(repository.findById(roleDTO.getId())).thenReturn(null);
        service.updateRole(roleDTO);
    }
}