package ru.javaschool.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.sbb.DAO.api.RoleDAO;
import ru.javaschool.sbb.DTO.RoleDTO;
import ru.javaschool.sbb.entity.Role;
import ru.javaschool.sbb.mapper.RoleMapper;
import ru.javaschool.sbb.service.api.RoleService;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;
    private final RoleMapper roleMapper;


    @Override
    @Transactional
    public RoleDTO findRoleDTOByTitle(String title) {
        Role role = roleDAO.findRoleByTitle(title);
        return roleMapper.toDTO(role);
    }

    @Override
    public RoleDTO getRoleDTOById(int id) {
        Role role = roleDAO.getRoleById(id);
        return roleMapper.toDTO(role);
    }
}
