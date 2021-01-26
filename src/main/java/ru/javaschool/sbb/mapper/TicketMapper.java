package ru.javaschool.sbb.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javaschool.sbb.DAO.api.StationDAO;
import ru.javaschool.sbb.DAO.api.TrainDAO;
import ru.javaschool.sbb.DAO.api.TripDAO;
import ru.javaschool.sbb.DTO.TicketDTO;
import ru.javaschool.sbb.entity.Ticket;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TicketMapper{

    private final TrainDAO trainDAO;
    private final TripDAO tripDAO;
    private final StationDAO stationDAO;
    private final UserMapper userMapper;
    private final PassengerMapper passengerMapper;



    public TicketDTO toDTO(Ticket ticket) {
        if (ticket == null) {
            return null;
        }
        TicketDTO ticketDTO = TicketDTO.builder()
                .id(ticket.getId())
                .trainId(ticket.getTrain().getId())
                .tripId(ticket.getTrip().getId())
                .stationFromId(ticket.getStationFrom().getId())
                .stationToId(ticket.getStationTo().getId())
                .departureTime(ticket.getDepartureTime())
                .arrivalTime(ticket.getArrivalTime())
                .passengerDTO(passengerMapper.toDTO(ticket.getPassenger()))
                .userDTO(userMapper.toDTO(ticket.getUser()))
                .build();

        return ticketDTO;
    }

    public Ticket toEntity(TicketDTO ticketDTO) {
        if (ticketDTO == null) {
            return null;
        }

        Ticket ticket = Ticket.builder()
                .id(ticketDTO.getId())
                .train(trainDAO.getTrain(ticketDTO.getTrainId()))
                .trip(tripDAO.getTrip(ticketDTO.getTripId()))
                .stationFrom(stationDAO.getStation(ticketDTO.getStationFromId()))
                .stationTo(stationDAO.getStation(ticketDTO.getStationToId()))
                .departureTime(ticketDTO.getDepartureTime())
                .arrivalTime(ticketDTO.getArrivalTime())
                .user(userMapper.toEntity(ticketDTO.getUserDTO()))
                .passenger(passengerMapper.toEntity(ticketDTO.getPassengerDTO()))
                .build();

        return ticket;
    }

    public List<TicketDTO> toDTOList(List<Ticket> ticketList) {
        if (ticketList == null) {
            return null;
        }

        List<TicketDTO> list = new ArrayList<>(ticketList.size());
        for (Ticket ticket : ticketList) {
            list.add(toDTO(ticket));
        }

        return list;
    }

    public List<Ticket> toEntityList(List<TicketDTO> ticketDTOList) {

        if (ticketDTOList == null) {
            return null;
        }

        List<Ticket> list = new ArrayList<>(ticketDTOList.size());
        for (TicketDTO ticketDTO : ticketDTOList) {
            list.add(toEntity(ticketDTO));
        }
        return list;
    }
}


