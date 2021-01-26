package ru.javaschool.sbb.DAO.api;

import ru.javaschool.sbb.DTO.StationDTO;
import ru.javaschool.sbb.entity.Station;
import ru.javaschool.sbb.entity.Trip;

import java.util.List;

public interface TripDAO {


    Trip getTrip(int id);

    List<Trip> getAllTrips();

    void addTrip(Trip trip);

    void editTrip(Trip trip);

    void deleteTrip(Trip trip);

    List<Trip> getTripsByTrainId(int trainId);


}
