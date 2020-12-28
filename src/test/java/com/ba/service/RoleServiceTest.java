package com.ba.service;

import com.ba.builder.RoleBuilder;
import com.ba.builder.RoleDTOBuilder;
import com.ba.dto.RoleDTO;
import com.ba.entity.Role;
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
    public void shouldVerifyListRoles() {
        when(repository.findAll()).thenReturn(roleList);

        List<RoleDTO> tempDTOList = roleMapper.toDTOList(roleList);
//        List<RoleDTO> tempDTOList = RoleConverter.convertListToDTOList(roleList);
        List<RoleDTO> tempDTOList2 = service.listRoles();

        assertEquals(tempDTOList.get(0).getId(),tempDTOList2.get(0).getId());
    }

    @Test
    public void shouldVerifyAddRole() {
        when(repository.save(role)).thenReturn(role);
        String res = service.addRole(roleDTO);

        assertNotNull(res);
        assertEquals(res,"Role Added");
    }

    @Test
    public void shouldVerifyDeleteRole() {
        String res = service.deleteRole(id);
        assertEquals(res,"Role Deleted");
        verify(repository,times(1)).deleteById(id);
    }

    @Test
    public void shouldVerifyUpdateRole() {
        String res = service.updateRole(roleDTO);
        assertNotNull(res);
        assertEquals(res,"Role Updated");
    }
}