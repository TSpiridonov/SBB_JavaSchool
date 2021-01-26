package ru.javaschool.sbb.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {

    private int id;

    private TripDTO tripDTO;

    private int stationIndex;

    private StationDTO stationFromDTO;

    private StationDTO stationToDTO;

    private Timestamp departureTime;

    private Timestamp arrivalTime;

    private int freePlacesCount;

    private boolean availableOnTime;

    private List<ScheduleDTO> scheduleDTOList;

}

