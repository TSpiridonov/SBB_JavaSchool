package ru.javaschool.sbb.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDTO {

    private int id;

    @NotBlank(message = "*Passenger name can't be empty.")
    @Size(min = 1, max = 100, message = "*Name must be between 1 and 100 characters.")
    @Pattern(regexp = "^[a-zA-Z \\-]+$", message = "*Please enter valid name.")
    private String name;

    @NotBlank(message = "*Passenger surname can't be empty.")
    @Size(min = 1, max = 100, message = "*Surname must be between 1 and 100 characters.")
    @Pattern(regexp = "^[a-zA-Z \\-]+$", message = "*Please enter valid surname.")
    private String surname;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "*Date of birth can't be later than now")
    private Date birthDate;

}
