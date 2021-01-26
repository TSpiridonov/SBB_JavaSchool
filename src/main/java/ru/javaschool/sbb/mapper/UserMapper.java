package ru.javaschool.sbb.mapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javaschool.sbb.DTO.UserDTO;
import ru.javaschool.sbb.entity.User;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper{

    @Autowired
    private RoleMapper roleMapper;

    public UserDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setRoleDTO( roleMapper.toDTO( user.getRole() ) );
        userDTO.setId( user.getId() );
        userDTO.setUsername( user.getUsername() );
        userDTO.setPassword( user.getPassword() );
        userDTO.setConfirmPassword( user.getConfirmPassword() );

        return userDTO;
    }

    public User toEntity(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        User user = new User();

        user.setRole( roleMapper.toEntity( userDTO.getRoleDTO() ) );
        user.setId( userDTO.getId() );
        user.setUsername( userDTO.getUsername() );
        user.setPassword( userDTO.getPassword() );
        user.setConfirmPassword( userDTO.getConfirmPassword() );

        return user;
    }

    public List<UserDTO> toDTOList(List<User> userList) {
        if ( userList == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( userList.size() );
        for ( User user : userList ) {
            list.add( toDTO( user ) );
        }

        return list;
    }

    public List<User> toEntityList(List<UserDTO> userDTOList) {
        if ( userDTOList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( userDTOList.size() );
        for ( UserDTO userDTO : userDTOList ) {
            list.add( toEntity( userDTO ) );
        }

        return list;
    }
}
