package ru.javaschool.sbb.service.api;


import ru.javaschool.sbb.DTO.TicketDTO;
import ru.javaschool.sbb.DTO.TicketInfo;

import java.sql.Timestamp;
import java.util.List;


public interface TicketService {

    void addTicket(TicketDTO ticketDTO);

    void editTicket(TicketDTO ticketDTO);

    void deleteTicket(TicketDTO ticketDTO);

    boolean isTrainFull(Timestamp departureTime, Timestamp arrivalTime, int trainId, int tripId);

    boolean isTimeValidToBuy(Timestamp departureTime);

    List<TicketInfo> getAllUserTickets(String username);


}
