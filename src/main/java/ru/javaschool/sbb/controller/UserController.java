package ru.javaschool.sbb.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.javaschool.sbb.DTO.UserDTO;
import ru.javaschool.sbb.service.api.RoleService;
import ru.javaschool.sbb.service.api.SecurityService;
import ru.javaschool.sbb.service.api.UserService;
import ru.javaschool.sbb.validator.UserValidator;



@Controller
public class UserController {

    @Autowired
    private SecurityService securityService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;


    @GetMapping("/registration")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("RegistrationPage");
        modelAndView.addObject("userForm", new UserDTO());
        return modelAndView;
    }

    @PostMapping(value = "/registration")
    public ModelAndView register(@Valid @ModelAttribute("userForm") UserDTO userDTO,
                                 BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        userValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("RegistrationPage");
            return modelAndView;
        }
        userDTO.setRoleDTO(roleService.getRoleDTOById(1));
        
        userService.register(userDTO);

        securityService.autoLogin(userDTO.getUsername(), userDTO.getConfirmPassword());

        modelAndView.setViewName("redirect:/?success");

        return modelAndView;
    }

}
