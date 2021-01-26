package ru.javaschool.sbb.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import ru.javaschool.sbb.DTO.RoleDTO;
import ru.javaschool.sbb.entity.Role;

import javax.annotation.Generated;

@Component
public class RoleMapper{

    public Role toEntity(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        Role role =  new Role();

        role.setId( roleDTO.getId() );
        role.setRoleTitle( roleDTO.getRoleTitle()    );

        return role;
    }

    public RoleDTO toDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTO roleDTO = new  RoleDTO();

        roleDTO.setId( role.getId() );
        roleDTO.setRoleTitle( role.getRoleTitle() );

        return roleDTO;
    }
}
