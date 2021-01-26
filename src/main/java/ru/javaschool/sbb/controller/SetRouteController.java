package ru.javaschool.sbb.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.javaschool.sbb.DTO.RouteContainer;
import ru.javaschool.sbb.DTO.RouteDTO;
import ru.javaschool.sbb.DTO.StationDTO;
import ru.javaschool.sbb.DTO.TrainDTO;
import ru.javaschool.sbb.service.api.RouteContainerService;
import ru.javaschool.sbb.service.api.ScheduleService;
import ru.javaschool.sbb.service.api.StationService;
import ru.javaschool.sbb.service.api.TrainService;
import ru.javaschool.sbb.utility.CollectionUtils;
import ru.javaschool.sbb.validator.RouteValidator;
import ru.javaschool.sbb.validator.StartRouteValidator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class SetRouteController{

    @Autowired
    private StartRouteValidator startRouteValidator;
    @Autowired
    private RouteValidator routeValidator;
    @Autowired
    private TrainService trainService;
    @Autowired
    private StationService stationService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private RouteContainer routeContainer;
    @Autowired
    private RouteContainerService containerService;


    @ModelAttribute("stationsList")
    public List<StationDTO> getAllStations(){
        return stationService.getAllStationsDTO();
    }

    @ModelAttribute("trainsList")
    public List<TrainDTO> getAllTrains(){
        return trainService.getAllTrainsDTO();
    }

    @GetMapping("/setroute")
    public ModelAndView setRoute(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if(request.getParameter("start") != null){
            containerService.truncateContainer();
        }
        modelAndView.addObject("routeDTO", new RouteDTO());
        modelAndView.addObject("routeContainer", routeContainer);
        modelAndView.setViewName("SetRoutePage");
        return modelAndView;
    }

    @PostMapping("/trainselect")
    public ModelAndView selectTrain(@Valid @ModelAttribute("routeDTO") RouteDTO routeDTO, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        System.out.println(routeDTO);
        startRouteValidator.validate(routeDTO, bindingResult);
        if(bindingResult.hasErrors()){
            modelAndView.addObject("error", "error");
            modelAndView.setViewName("SetRoutePage");
            return modelAndView;
        }
        containerService.setInitialInfo(routeDTO);
        modelAndView.setViewName("redirect:/admin/setroute");
        return modelAndView;
    }

    @PostMapping("/setroute")
    public ModelAndView setRoute(@ModelAttribute("routeDTO") RouteDTO routeDTO, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView();
        System.out.println(routeDTO);
        routeValidator.validate(routeDTO, bindingResult);

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("routeContainer", routeContainer);
            modelAndView.setViewName("SetRoutePage");
            return modelAndView;
        }

        if (CollectionUtils.isEmpty(routeContainer.getSideStations())) {
            containerService.setSegmentsInfo(routeDTO);
        } else {
            containerService.updateSegmentsInfo(routeDTO);
        }

        modelAndView.setViewName("redirect:/admin/setroute");
        return modelAndView;
    }

    @GetMapping(value = "/deleteLast")
    public String deleteLastChange() {
        if (CollectionUtils.isNotEmpty(routeContainer.getSideStations())
                && CollectionUtils.isNotEmpty(routeContainer.getSideArrivalTimes())) {
            containerService.deleteLastChange();
            return "redirect:/admin/setroute";
        }
        return "redirect:/admin/setroute?start";
    }

    @GetMapping("/createtrip")
    public RedirectView createTrip(RedirectAttributes redirectAttributes) {
        RedirectView redirectView = new RedirectView("/admin/management",true);
        scheduleService.createSchedulesAndTrip();
        redirectAttributes.addFlashAttribute("successMessage", "Trip was created.");
        containerService.truncateContainer();
        return redirectView;
    }

}
