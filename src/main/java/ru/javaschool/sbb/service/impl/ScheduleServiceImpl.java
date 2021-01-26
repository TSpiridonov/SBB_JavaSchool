package ru.javaschool.sbb.service.impl;

import com.mysql.cj.protocol.MessageSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.sbb.DAO.api.*;
import ru.javaschool.sbb.DTO.RouteContainer;
import ru.javaschool.sbb.DTO.ScheduleDTO;
import ru.javaschool.sbb.DTO.StationDTO;
import ru.javaschool.sbb.entity.*;
import ru.javaschool.sbb.mapper.ScheduleMapper;
import ru.javaschool.sbb.mapper.StationMapper;
import ru.javaschool.sbb.mapper.TrainMapper;
import ru.javaschool.sbb.service.api.ScheduleService;
import ru.javaschool.sbb.service.api.TicketService;
import ru.javaschool.sbb.utility.CollectionUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleMapper scheduleMapper;
    private final StationMapper stationMapper;
    private final ScheduleDAO scheduleDAO;
    private final StationDAO stationDAO;
    private final TrainDAO trainDAO;
    private final TrainMapper trainMapper;
    private final TicketDAO ticketDAO;
    private final TicketService ticketService;
    private final RouteContainer routeContainer;
    private final TripDAO tripDAO;


    @Override
    public void addSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleMapper.toEntity(scheduleDTO);
        scheduleDAO.addSchedule(schedule);
    }

    @Override
    public void editSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleMapper.toEntity(scheduleDTO);
        scheduleDAO.editSchedule(schedule);
    }

    @Override
    public void deleteSchedule(ScheduleDTO scheduleDTO) {
        Schedule schedule = scheduleMapper.toEntity(scheduleDTO);
        scheduleDAO.deleteSchedule(schedule);
    }

    @Override
    @Transactional
    public void createSchedulesAndTrip() {
        
        Train train = trainMapper.toEntity(routeContainer.getTrainDTO());

        Station departureStation = stationMapper.toEntity(routeContainer.getDepartureStation());

        Timestamp departureTime = Timestamp.valueOf(LocalDateTime.parse(routeContainer.getDepartureDate()));

        int stationIndex = 0;

        List<Station> stations = new ArrayList<>();

        stations.add(departureStation);

        stations.addAll(stationMapper.toEntityList(routeContainer.getSideStations()));

        List<LocalDateTime> arrivalDates = routeContainer.getSideArrivalTimes()
                .stream()
                .map(LocalDateTime::parse)
                .collect(Collectors.toList());

        List<Integer> stops = routeContainer.getStops()
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Trip trip = new Trip(train, departureStation, CollectionUtils.getLast(stations),
                departureTime, Timestamp.valueOf(CollectionUtils.getLast(arrivalDates)));

        tripDAO.addTrip(trip);

        for (int i = 0; i < routeContainer.getSideStations().size(); i++) {
            Schedule schedule = new Schedule();
            schedule.setTrip(trip);
            schedule.setStationIndex(++stationIndex);
            schedule.setStationFrom(stations.get(i));
            schedule.setStationTo(stations.get(i + 1));
            if (i == 0) {
                schedule.setDepartureTime(departureTime);
            } else {
                schedule.setDepartureTime(Timestamp.valueOf(arrivalDates.get(i - 1).plusMinutes(stops.get(i - 1))));
            }

            schedule.setArrivalTime(Timestamp.valueOf(arrivalDates.get(i)));

            scheduleDAO.addSchedule(schedule);
        }

        String message = "New trip was created!";
        log.info("Message: '" + message + "' was sent into topic.");
    }



    @Override
    @Transactional
    public List<ScheduleDTO> getSchedulesByStationFrom(StationDTO stationDTOFrom) {
        Station stationFrom = stationMapper.toEntity(stationDTOFrom);
        List<ScheduleDTO> resultSchedules = scheduleMapper
                .toDTOList(scheduleDAO.getSchedulesByStationFrom(stationFrom));

        for (ScheduleDTO scheduleDTO : resultSchedules) {
            scheduleDTO.setScheduleDTOList(getSchedulesByTripId(scheduleDTO.getTripDTO().getId()));
        }

        return resultSchedules;
    }

    @Override
    @Transactional
    public List<ScheduleDTO> getSchedulesByStationTo(StationDTO stationDTOTo) {
        Station stationTo = stationMapper.toEntity(stationDTOTo);
        List<ScheduleDTO> resultSchedules = scheduleMapper
                .toDTOList(scheduleDAO.getSchedulesByStationTo(stationTo));
        for (ScheduleDTO scheduleDTO : resultSchedules) {
            scheduleDTO.setScheduleDTOList(getSchedulesByTripId(scheduleDTO.getTripDTO().getId()));
        }
        return resultSchedules;
    }

    @Override
    @Transactional
    public List<ScheduleDTO> getSchedulesByStationsAndDate(Integer stationFromId, Integer stationToId,
                                                           String dateTimeFrom, String dateTimeTo) {

        Station stationFrom = stationDAO.getStation(stationFromId);
        Station stationTo = stationDAO.getStation(stationToId);
        Timestamp dateFrom = Timestamp.valueOf(LocalDateTime.parse(dateTimeFrom));
        Timestamp dateTo = Timestamp.valueOf(LocalDateTime.parse(dateTimeTo));

        List<ScheduleDTO> schedulesByStationFromAndDate = scheduleMapper
                .toDTOList(scheduleDAO.getSchedulesByDepartureStationAndTime(stationFrom, dateFrom, dateTo));


        List<ScheduleDTO> resultSchedules = new ArrayList<>();

        for (ScheduleDTO scheduleFrom : schedulesByStationFromAndDate) {
            List<ScheduleDTO> schedulesByStationToAndTrip = scheduleMapper
                    .toDTOList(scheduleDAO.getSchedulesByTripIdAndStationTo(scheduleFrom.getTripDTO().getId(),
                            stationTo));

            if (CollectionUtils.isEmpty(schedulesByStationToAndTrip)) {
                continue;
            }

            for (ScheduleDTO scheduleTo : schedulesByStationToAndTrip) {
                if (scheduleFrom.getId() == scheduleTo.getId()) {
                    setInfo(scheduleFrom);
                    resultSchedules.add(scheduleFrom);

                } else if (scheduleFrom.getStationIndex() < scheduleTo.getStationIndex()) {
                    scheduleTo.setStationFromDTO(scheduleFrom.getStationFromDTO());
                    scheduleTo.setDepartureTime(scheduleFrom.getDepartureTime());
                    setInfo(scheduleTo);
                    resultSchedules.add(scheduleTo);
                }
            }
        }

        return resultSchedules;
    }


    private void setInfo(ScheduleDTO scheduleDTO) {

        scheduleDTO.setScheduleDTOList(getSchedulesByTripId(scheduleDTO.getTripDTO().getId()));

        int freePlaces = scheduleDTO.getTripDTO().getTrainDTO().getCapacity() -
                ticketDAO.getTakenSeatsCount(scheduleDTO.getTripDTO().getTrainDTO().getId(),
                        scheduleDTO.getTripDTO().getId(), scheduleDTO.getDepartureTime(),
                        scheduleDTO.getArrivalTime()).intValue();

        scheduleDTO.setFreePlacesCount(Math.max(freePlaces, 0));

        scheduleDTO.setAvailableOnTime(ticketService.isTimeValidToBuy(scheduleDTO.getDepartureTime()));
    }


    @Override
    @Transactional
    public List<ScheduleDTO> getSchedulesByTripId(int tripId) {
        return scheduleMapper
                .toDTOList(scheduleDAO.getSchedulesByTripId(tripId));
    }

}
