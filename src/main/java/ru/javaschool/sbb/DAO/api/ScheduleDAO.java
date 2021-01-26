package ru.javaschool.sbb.DAO.api;

import ru.javaschool.sbb.DTO.TimetableDTO;
import ru.javaschool.sbb.entity.Schedule;
import ru.javaschool.sbb.entity.Station;
import ru.javaschool.sbb.entity.Train;

import java.sql.Timestamp;
import java.util.List;

public interface ScheduleDAO {

    List<Schedule> getSchedulesByDepartureStationAndTime(Station stationFrom, Timestamp dateFrom, Timestamp dateTo);

    List<Schedule> getSchedulesByTripIdAndStationTo(int tripId, Station stationTo);

    List<Schedule> getSchedulesByStationFrom(Station stationFrom);

    List<Schedule> getSchedulesByStationTo(Station stationTo);

    List<Schedule> getSchedulesByTripId(int tripId);

    void addSchedule(Schedule schedule);

    void editSchedule(Schedule schedule);

    void deleteSchedule(Schedule schedule);

    List<TimetableDTO> getDepartureTimetableByStationId(int stationId);


    List<TimetableDTO> getArrivalTimetableByStationId(int stationId);

}
