package ru.javaschool.sbb.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PassengerInfo {

    public String trainNumber;

    private String name;

    private String surname;

    private Date birthDate;

    private String stationFrom;

    private String stationTo;

    private Timestamp departureTime;

    private Timestamp arrivalTime;

}
