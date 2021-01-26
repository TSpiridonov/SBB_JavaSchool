package ru.javaschool.sbb.service.api;


import ru.javaschool.sbb.DTO.UserDTO;

public interface UserService {

    UserDTO findUserDTOByName(String username);

    void register(UserDTO userDTO);
}
