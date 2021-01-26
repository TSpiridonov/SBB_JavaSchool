package ru.javaschool.sbb.controller;


import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.javaschool.sbb.DTO.StationDTO;
import ru.javaschool.sbb.DTO.TimetableDTO;
import ru.javaschool.sbb.service.api.StationService;
import ru.javaschool.sbb.service.api.TimetableService;

import java.util.List;

@Log4j2
@RestController
public class TimetableRestController {

    @Autowired
    private StationService stationService;
    @Autowired
    private TimetableService timetableService;

    @GetMapping("/timetable/departure/{stationId}")
    public List<TimetableDTO> getDepartureTimetable(@PathVariable("stationId") Integer stationId) {
        return timetableService.getDepartureTimetable(stationId);
    }

    @GetMapping("/timetable/arrival/{stationId}")
    public List<TimetableDTO> getArrivalTimetable(@PathVariable("stationId") Integer stationId) {
        return timetableService.getArrivalTimetable(stationId);
    }

    @GetMapping("/allstations")
    public List<StationDTO> getAllStations(){
        return stationService.getAllStationsDTO();
    }


}
