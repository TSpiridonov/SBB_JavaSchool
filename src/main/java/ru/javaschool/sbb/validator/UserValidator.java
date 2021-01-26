package ru.javaschool.sbb.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javaschool.sbb.DTO.UserDTO;
import ru.javaschool.sbb.service.api.UserService;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        UserDTO userDTO = (UserDTO) o;

        if (userService.findUserDTOByName(userDTO.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }

    }
}