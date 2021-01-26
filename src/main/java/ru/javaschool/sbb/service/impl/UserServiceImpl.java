package ru.javaschool.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.sbb.DAO.api.UserDAO;
import ru.javaschool.sbb.DTO.UserDTO;
import ru.javaschool.sbb.entity.User;
import ru.javaschool.sbb.mapper.UserMapper;
import ru.javaschool.sbb.service.api.UserService;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public void register(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        user.setPassword(user.getPassword());
        userDAO.addUser(user);
    }

    @Override
    @Transactional
    public UserDTO findUserDTOByName(String username) {
        User user = userDAO.findUserByUsername(username);
        return userMapper.toDTO(user);
    }

}
