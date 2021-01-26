package ru.javaschool.sbb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.javaschool.sbb.DTO.ScheduleDTO;
import ru.javaschool.sbb.DTO.StationDTO;
import ru.javaschool.sbb.DTO.TicketDTO;
import ru.javaschool.sbb.service.api.ScheduleService;
import ru.javaschool.sbb.service.api.StationService;

import java.time.LocalDateTime;
import java.util.List;


@Controller
public class SBBController {

    @Autowired
    private StationService stationService;
    @Autowired
    private ScheduleService scheduleService;


    @ModelAttribute("stations")
    public List<StationDTO> getAllStations(){
        return stationService.getAllStationsDTO();
    }

    @ModelAttribute("currentDateTime")
    public LocalDateTime now(){
        return LocalDateTime.now().withSecond(0).withNano(0);
    }



    @GetMapping(value = "/")
    public ModelAndView getMainPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("MainPage");
        return modelAndView;
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Login");
        modelAndView.addObject("logoutmessage", "You have been logged out successfully.");
        return modelAndView;
    }

    @GetMapping(value = "/schedule")
    public ModelAndView searchTrips(@RequestParam("stationFrom") Integer stationFromId,
                                    @RequestParam("stationTo") Integer stationToId,
                                    @RequestParam("dateFrom") String dateFrom,
                                    @RequestParam("dateTo") String dateTo,
                                    @RequestParam(value = "err", required = false) String err) {

        ModelAndView modelAndView = new ModelAndView();

        List<ScheduleDTO> list = scheduleService
                .getSchedulesByStationsAndDate(stationFromId, stationToId, dateFrom, dateTo);

        modelAndView.addObject("ticket", new TicketDTO());
        modelAndView.addObject("scheduleDTOList", list);
        modelAndView.setViewName("SchedulePage");
        return modelAndView;
    }


    @GetMapping(value = "/timetable")
    public ModelAndView getTimetable(@RequestParam(name = "stationId") Integer stationId) {
        ModelAndView modelAndView = new ModelAndView();
        StationDTO stationDTO = stationService.getStationDTOById(stationId);
        List<ScheduleDTO> scheduleDTOsFrom = scheduleService.getSchedulesByStationFrom(stationDTO);
        List<ScheduleDTO> scheduleDTOsTo = scheduleService.getSchedulesByStationTo(stationDTO);
        modelAndView.addObject("station", stationDTO);
        modelAndView.addObject("scheduleDTOListFrom", scheduleDTOsFrom);
        modelAndView.addObject("scheduleDTOListTo", scheduleDTOsTo);
        modelAndView.setViewName("TimeTable");
        return modelAndView;
    }

}
