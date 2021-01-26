package ru.javaschool.sbb.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketInfo {

    int ticketId;

    String trainNumber;

    String name;

    String surname;

    Date birthDate;

    String statFromTitle;

    String statToTitle;

    Date departureTime;

    Date arrivalTime;

}
