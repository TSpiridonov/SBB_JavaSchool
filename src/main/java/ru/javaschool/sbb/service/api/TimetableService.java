package ru.javaschool.sbb.service.api;


import ru.javaschool.sbb.DTO.TimetableDTO;

import java.util.List;


public interface TimetableService {

    List<TimetableDTO> getDepartureTimetable(Integer stationId);


    List<TimetableDTO> getArrivalTimetable(Integer stationId);

}
