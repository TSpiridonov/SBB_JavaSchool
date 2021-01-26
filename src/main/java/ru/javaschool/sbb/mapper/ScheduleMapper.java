package ru.javaschool.sbb.mapper;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import ru.javaschool.sbb.DTO.ScheduleDTO;
import ru.javaschool.sbb.entity.Schedule;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ScheduleMapper{

    private final TripMapper tripMapper;
    private final StationMapper stationMapper;

    public ScheduleDTO toDTO(Schedule schedule) {
        if ( schedule == null ) {
            return null;
        }

        ScheduleDTO scheduleDTO = new  ScheduleDTO();

        scheduleDTO.setStationFromDTO( stationMapper.toDTO( schedule.getStationFrom() ) );
        scheduleDTO.setStationToDTO( stationMapper.toDTO( schedule.getStationTo() ) );
        scheduleDTO.setTripDTO( tripMapper.toDTO( schedule.getTrip() ) );
        scheduleDTO.setId( schedule.getId() );
        scheduleDTO.setStationIndex( schedule.getStationIndex() );
        scheduleDTO.setDepartureTime( schedule.getDepartureTime() );
        scheduleDTO.setArrivalTime( schedule.getArrivalTime() );

        return scheduleDTO;
    }

    public Schedule toEntity(ScheduleDTO scheduleDTO) {
        if ( scheduleDTO == null ) {
            return null;
        }

        Schedule schedule = new  Schedule();

        schedule.setTrip( tripMapper.toEntity( scheduleDTO.getTripDTO() ) );
        schedule.setStationFrom( stationMapper.toEntity( scheduleDTO.getStationFromDTO() ) );
        schedule.setStationTo( stationMapper.toEntity( scheduleDTO.getStationToDTO() ) );
        schedule.setId( scheduleDTO.getId() );
        schedule.setStationIndex( scheduleDTO.getStationIndex() );
        schedule.setDepartureTime( scheduleDTO.getDepartureTime() );
        schedule.setArrivalTime( scheduleDTO.getArrivalTime() );

        return schedule;
    }

    public List<ScheduleDTO> toDTOList(List<Schedule> scheduleList) {
        if ( scheduleList == null ) {
            return null;
        }

        List<ScheduleDTO> list = new ArrayList<ScheduleDTO>( scheduleList.size() );
        for ( Schedule schedule : scheduleList ) {
            list.add( toDTO( schedule ) );
        }

        return list;
    }

    public List<Schedule> toEntityList(List<ScheduleDTO> scheduleDTOList) {
        if ( scheduleDTOList == null ) {
            return null;
        }

        List<Schedule> list = new ArrayList<Schedule>( scheduleDTOList.size() );
        for ( ScheduleDTO scheduleDTO : scheduleDTOList ) {
            list.add( toEntity( scheduleDTO ) );
        }

        return list;
    }
}
