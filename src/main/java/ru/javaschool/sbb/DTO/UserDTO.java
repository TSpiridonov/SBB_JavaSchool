package ru.javaschool.sbb.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private int id;

    @Email
    @Size(min = 6, max = 50, message = "*Email must be between 6 and 50 characters.")
    private String username;

    @NotBlank(message = "*Password can't be empty.")
    @Size(min = 4, max = 50, message = "*Password must be between 4 and 50 characters.")
    private String password;

    private String confirmPassword;

    private RoleDTO roleDTO;
}
