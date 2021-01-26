package ru.javaschool.sbb.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.javaschool.sbb.DTO.TripDTO;
import ru.javaschool.sbb.entity.Trip;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TripMapper{

    private final TrainMapper trainMapper;
    private final StationMapper stationMapper;

    public TripDTO toDTO(Trip trip) {
        if ( trip == null ) {
            return null;
        }

        TripDTO tripDTO = new  TripDTO();

        tripDTO.setTrainDTO( trainMapper.toDTO( trip.getTrain() ) );
        tripDTO.setArrivalStationDTO( stationMapper.toDTO( trip.getArrivalStation() ) );
        tripDTO.setDepartureStationDTO( stationMapper.toDTO( trip.getDepartureStation() ) );
        tripDTO.setId( trip.getId() );
        tripDTO.setDepartureTime( trip.getDepartureTime() );
        tripDTO.setArrivalTime( trip.getArrivalTime() );

        return tripDTO;
    }

    public Trip toEntity(TripDTO tripDTO) {
        if ( tripDTO == null ) {
            return null;
        }

        Trip trip = new Trip();

        trip.setDepartureStation( stationMapper.toEntity( tripDTO.getDepartureStationDTO() ) );
        trip.setArrivalStation( stationMapper.toEntity( tripDTO.getArrivalStationDTO() ) );
        trip.setTrain( trainMapper.toEntity( tripDTO.getTrainDTO() ) );
        trip.setId( tripDTO.getId() );
        trip.setDepartureTime( tripDTO.getDepartureTime() );
        trip.setArrivalTime( tripDTO.getArrivalTime() );

        return trip;
    }


    public List<TripDTO> toDTOList(List<Trip> trips) {
        if ( trips == null ) {
            return null;
        }

        List<TripDTO> list = new ArrayList<TripDTO>( trips.size() );
        for ( Trip trip : trips ) {
            list.add( toDTO( trip ) );
        }

        return list;
    }

    public List<Trip> toEntityList(List<TripDTO> tripDTOs) {
        if ( tripDTOs == null ) {
            return null;
        }

        List<Trip> list = new ArrayList<Trip>( tripDTOs.size() );
        for ( TripDTO tripDTO : tripDTOs ) {
            list.add( toEntity( tripDTO ) );
        }

        return list;
    }
}
