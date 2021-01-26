package ru.javaschool.sbb.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RouteDTO {

    @NotNull(message = "Train is not selected!")
    private Integer trainId;

    @NotNull(message = "Station is not selected!")
    private Integer departureStationId;

    @NotBlank(message = "*Departure time is not selected.")
    private String departureDate;

    @NotBlank(message = "*Arrival time is not selected.")
    private String declaredArrivalDate;

    private List<String> sideStations;

    private List<String> sideArrivalTimes;

    private List<String> stops;
}


