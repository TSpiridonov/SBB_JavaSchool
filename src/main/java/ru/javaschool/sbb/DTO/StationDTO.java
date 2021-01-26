package ru.javaschool.sbb.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StationDTO {

    private int id;

    @NotBlank(message = "*This field cant be empty.")
    @Size(min = 3, max = 50, message = "*Title must be between 3 and 50 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9 .\\-]+$", message = "*Invalid symbol (only spaces,hyphens or dots are allowed).")
    private String title;
}
