package ru.javaschool.sbb.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainDTO {

    private int id;

    @NotBlank(message = "*This field can't be empty.")
    @Size(min = 5, max = 10, message = "*Number must be between 5 and 10 characters.")
    @Pattern(regexp = "^[a-zA-Z0-9 \\-]+$", message = "*Invalid symbol (only spaces or hyphens are allowed).")
    private String trainNumber;

    @Range(min = 1, max = 300, message = "*Capacity must be between 1 and 300 seats.")
    private int capacity;

}
