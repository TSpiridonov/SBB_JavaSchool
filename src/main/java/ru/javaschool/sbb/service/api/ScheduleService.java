package ru.javaschool.sbb.service.api;


import ru.javaschool.sbb.DTO.ScheduleDTO;
import ru.javaschool.sbb.DTO.StationDTO;
import ru.javaschool.sbb.entity.Schedule;

import java.util.List;

public interface ScheduleService {

    void createSchedulesAndTrip();

    void addSchedule(ScheduleDTO scheduleDTO);

    void editSchedule(ScheduleDTO scheduleDTO);

    void deleteSchedule(ScheduleDTO scheduleDTO);

    List<ScheduleDTO> getSchedulesByStationFrom(StationDTO stationDTOFrom);

    List<ScheduleDTO> getSchedulesByStationTo(StationDTO stationDTOTo);

    List<ScheduleDTO> getSchedulesByStationsAndDate(Integer stationFromId, Integer stationToId, String dateTimeFrom,
                                                    String dateTimeTo);

    List<ScheduleDTO> getSchedulesByTripId(int tripId);



}
