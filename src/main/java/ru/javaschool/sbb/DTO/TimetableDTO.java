package ru.javaschool.sbb.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TimetableDTO {

    private String trainNumber;

    private String stationFrom;

    private String stationTo;

    private Date departureTime;

    private Date arrivalTime;


}

