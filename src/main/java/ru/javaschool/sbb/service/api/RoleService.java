package ru.javaschool.sbb.service.api;


import ru.javaschool.sbb.DTO.RoleDTO;

public interface RoleService {
    RoleDTO findRoleDTOByTitle(String title);

    RoleDTO getRoleDTOById(int id);


}
