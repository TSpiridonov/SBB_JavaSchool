package ru.javaschool.sbb.DAO.api;


import ru.javaschool.sbb.entity.Passenger;
import ru.javaschool.sbb.entity.Ticket;
import ru.javaschool.sbb.entity.Train;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface TicketDAO {

    Ticket getTicket(int id);

    List<Ticket> getAllTickets();

    void addTicket(Ticket ticket);

    void editTicket(Ticket ticket);

    void deleteTicket(Ticket ticket);

    BigInteger getTakenSeatsCount(int trainId, int tripId, Timestamp departureTime, Timestamp arrivalTime);


    List<Object[]> getAllTicketsByUserId(int userId);

}