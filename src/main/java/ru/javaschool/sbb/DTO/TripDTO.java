package ru.javaschool.sbb.DTO;

import lombok.AllArgsConstructor;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TripDTO {

    private int id;

    private TrainDTO trainDTO;

    private StationDTO departureStationDTO;

    private StationDTO arrivalStationDTO;

    private Timestamp departureTime;

    private Timestamp arrivalTime;

    private List<ScheduleDTO> scheduleList;


}
