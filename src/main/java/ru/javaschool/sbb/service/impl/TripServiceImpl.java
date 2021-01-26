package ru.javaschool.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.sbb.DAO.api.ScheduleDAO;
import ru.javaschool.sbb.DAO.api.TrainDAO;
import ru.javaschool.sbb.DAO.api.TripDAO;
import ru.javaschool.sbb.DTO.TripDTO;
import ru.javaschool.sbb.entity.Trip;
import ru.javaschool.sbb.mapper.TripMapper;
import ru.javaschool.sbb.service.api.ScheduleService;
import ru.javaschool.sbb.service.api.TripService;

import java.time.LocalDateTime;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class TripServiceImpl implements TripService {

    private final TrainDAO trainDAO;
    private final TripDAO tripDAO;
    private final TripMapper tripMapper;
    private final ScheduleDAO scheduleDAO;
    private final ScheduleService scheduleService;


    @Override
    @Transactional
    public TripDTO getTripDTOById(int id) {
        Trip trip = tripDAO.getTrip(id);
        return tripMapper.toDTO(trip);
    }


    @Override
    @Transactional
    public void add(TripDTO tripDTO) {
        Trip trip = tripMapper.toEntity(tripDTO);
        tripDAO.addTrip(trip);
    }

    @Override
    @Transactional
    public void edit(TripDTO tripDTO) {
        Trip trip = tripMapper.toEntity(tripDTO);
        tripDAO.editTrip(trip);
    }

    @Override
    @Transactional
    public void delete(TripDTO tripDTO) {
        Trip trip = tripMapper.toEntity(tripDTO);
        tripDAO.deleteTrip(trip);
    }

    @Override
    @Transactional
    public List<TripDTO> getAllTrips() {
        List<TripDTO> allTrips = tripMapper.toDTOList(tripDAO.getAllTrips());
        for(TripDTO trip : allTrips){
            trip.setScheduleList(scheduleService.getSchedulesByTripId(trip.getId()));
        }
        return allTrips;
    }

    @Override
    @Transactional
    public boolean isTrainAvailableForSuchTrip(Integer trainId, String departureTimeStr, String arrivalTimeStr) {

        LocalDateTime departureTime = LocalDateTime.parse(departureTimeStr);
        LocalDateTime arrivalTime = LocalDateTime.parse(arrivalTimeStr);
        List<Trip> trips = tripDAO.getTripsByTrainId(trainId);

        if (trips.isEmpty()) {
            return true;
        }

        for (Trip trip : trips) {

            LocalDateTime tripDeparture = trip.getDepartureTime().toLocalDateTime();

            LocalDateTime tripArrival = trip.getArrivalTime().toLocalDateTime();

            if ((departureTime.isAfter(tripDeparture) && departureTime.isBefore(tripArrival))
                    || (arrivalTime.isAfter(tripDeparture) && arrivalTime.isBefore(tripArrival))) {
                return false;
            }
            if (departureTime.isBefore(tripDeparture) && arrivalTime.isAfter(tripArrival)) {
                return false;
            }
            if ((departureTime.isEqual(tripDeparture) || departureTime.isEqual(tripArrival))
                    || (arrivalTime.isEqual(tripDeparture) || arrivalTime.isEqual(tripArrival))) {
                return false;
            }
        }

        return true;
    }


}


