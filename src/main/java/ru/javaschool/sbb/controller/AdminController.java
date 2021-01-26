package ru.javaschool.sbb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.javaschool.sbb.DTO.UserDTO;
import ru.javaschool.sbb.service.api.RoleService;
import ru.javaschool.sbb.service.api.UserService;
import ru.javaschool.sbb.validator.UserValidator;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private RoleService roleService;


    @GetMapping(value = "/management")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView adminMainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Management");
        return modelAndView;
    }

    @GetMapping("/addemployee")
    public ModelAndView registry(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("RegistrationPage");
        modelAndView.addObject("userForm", new UserDTO());
        return modelAndView;
    }


    @PostMapping(value = "/addemployee")
    public ModelAndView registry(@ModelAttribute("userForm") UserDTO userDTO, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        userValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("RegistrationPage");
            return modelAndView;
        }
        userDTO.setRoleDTO(roleService.getRoleDTOById(2));
        userService.register(userDTO);

        modelAndView.addObject("message", "New employee account created!");
        modelAndView.addObject("username", userDTO.getUsername());
        modelAndView.addObject("password", userDTO.getPassword());
        modelAndView.setViewName("Management");
        return modelAndView;
    }


}
