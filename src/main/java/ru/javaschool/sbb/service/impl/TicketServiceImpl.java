package ru.javaschool.sbb.service.impl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.javaschool.sbb.DAO.api.PassengerDAO;
import ru.javaschool.sbb.DAO.api.TicketDAO;
import ru.javaschool.sbb.DAO.api.TrainDAO;
import ru.javaschool.sbb.DAO.api.UserDAO;
import ru.javaschool.sbb.DTO.TicketDTO;
import ru.javaschool.sbb.DTO.TicketInfo;
import ru.javaschool.sbb.entity.Passenger;
import ru.javaschool.sbb.entity.Ticket;
import ru.javaschool.sbb.entity.User;
import ru.javaschool.sbb.exception.NoAvaliableTicketException;
import ru.javaschool.sbb.mapper.TicketMapper;
import ru.javaschool.sbb.service.api.TicketService;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TrainDAO trainDAO;
    private final TicketDAO ticketDAO;
    private final TicketMapper ticketMapper;
    private final UserDAO userDAO;
    private final PassengerDAO passengerDAO;

    @Override
    public void addTicket(TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.toEntity(ticketDTO);

        Passenger existingPassenger =
                passengerDAO.getPassengerByPersonalData(ticket.getPassenger().getName(),
                        ticket.getPassenger().getSurname(), ticket.getPassenger().getBirthDate());

        if (existingPassenger != null) {
            ticket.setPassenger(existingPassenger);
        } else {
            Passenger passenger = ticket.getPassenger();
            passengerDAO.addPassenger(passenger);
            ticket.setPassenger(passenger);
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        if (!username.equals("anonymousUser")) {
            ticket.setUser(userDAO.findUserByUsername(auth.getName()));
        }

        if (isTimeValidToBuy(ticket.getDepartureTime()) && !isTrainFull(ticket.getDepartureTime(), ticket.getArrivalTime(),
                ticket.getTrain().getId(), ticket.getTrip().getId())) {
            ticketDAO.addTicket(ticket);
        } else {
            throw new NoAvaliableTicketException();
        }
    }

    @Override
    public void editTicket(TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        ticketDAO.addTicket(ticket);
    }

    @Override
    public void deleteTicket(TicketDTO ticketDTO) {
        Ticket ticket = ticketMapper.toEntity(ticketDTO);
        ticketDAO.deleteTicket(ticket);
    }

    @Override
    public boolean isTimeValidToBuy(Timestamp departureTime) {
        long departureTimeMillis = departureTime.getTime();
        long currentTimeMillis = Timestamp.valueOf(LocalDateTime.now().withNano(0)).getTime();
        return (departureTimeMillis - currentTimeMillis) >= 600000;
    }

    @Override
    public List<TicketInfo> getAllUserTickets(String username) {
            User user = userDAO.findUserByUsername(username);
            List<Object[]> tickets = ticketDAO.getAllTicketsByUserId(user.getId());

            List<TicketInfo> userTickets = new ArrayList<>();

            for (Object[] objects : tickets) {
                int ticketId = (int) objects[0];
                String trainNumber = (String) objects[1];
                String name = (String) objects[2];
                String surname = (String) objects[3];
                Date birthDate = (Date) objects[4];
                String statFromTitle = (String) objects[5];
                String statToTitle = (String) objects[6];
                Date departureTime = (Date) objects[7];
                Date arrivalTime = (Date) objects[8];

                userTickets.add(new TicketInfo(ticketId, trainNumber, name, surname, birthDate, statFromTitle,
                        statToTitle, departureTime, arrivalTime));
            }

            return userTickets;
    }


    @Override
    public boolean isTrainFull(Timestamp departureTime, Timestamp arrivalTime, int trainId, int tripId) {
        BigInteger bigInteger = ticketDAO.getTakenSeatsCount(trainId, tripId, departureTime, arrivalTime);
        return trainDAO.getTrain(trainId).getCapacity() <= bigInteger.longValue();
    }


}

