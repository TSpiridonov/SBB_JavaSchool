package ru.javaschool.sbb.service.api;

import ru.javaschool.sbb.DTO.TripDTO;
import ru.javaschool.sbb.entity.Trip;

import java.util.List;

public interface TripService {

    void add(TripDTO tripDTO);

    void edit(TripDTO tripDTO);

    void delete(TripDTO tripDTO);

    List<TripDTO> getAllTrips();

    TripDTO getTripDTOById(int id);

    boolean isTrainAvailableForSuchTrip(Integer trainId, String departureTimeStr, String arrivalTimeStr);






}
