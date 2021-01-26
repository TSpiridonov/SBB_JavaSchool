package ru.javaschool.sbb.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javaschool.sbb.DTO.RouteContainer;
import ru.javaschool.sbb.DTO.RouteDTO;
import ru.javaschool.sbb.DTO.StationDTO;
import ru.javaschool.sbb.utility.CollectionUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class RouteValidator implements Validator {

    @Autowired
    private RouteContainer container;

    @Override
    public boolean supports(Class<?> aClass) {
        return RouteDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        RouteDTO routeDTO = (RouteDTO) o;

        List<String> stationList = routeDTO.getSideStations();

        List<StationDTO> sideStations = container.getSideStations();

        if (CollectionUtils.isNotEmpty(stationList)) {
            if (CollectionUtils.isEmpty(sideStations)) {
                if (stationList.get(0).equals(String.valueOf(container.getDepartureStation().getId()))) {
                    errors.rejectValue("sideStations", "DuplicateStation.inRoute");
                }
            } else if (stationList.get(0).equals(String.valueOf(CollectionUtils.getLast(sideStations).getId()))) {
                errors.rejectValue("sideStations", "DuplicateStation.inRoute");
            }

        } else errors.rejectValue("sideStations", "Empty.station");



        List<String> arrTimes = routeDTO.getSideArrivalTimes();
        List<String> resultArrTimes = container.getSideArrivalTimes();
        List<String> resultStops = container.getStops();

        if (CollectionUtils.isNotEmpty(arrTimes)) {

            if (CollectionUtils.isEmpty(resultArrTimes)) {
                if (LocalDateTime.parse(arrTimes.get(0))
                        .compareTo(LocalDateTime.parse(container.getDeclaredArrivalDate())) > 0) {
                    errors.rejectValue("sideArrivalTimes", "TimeIsMoreThanMain");
                } else if (LocalDateTime.parse(arrTimes.get(0))
                        .compareTo(LocalDateTime.parse(container.getDepartureDate())) < 0) {
                    errors.rejectValue("sideArrivalTimes", "Invalid.routeTime");
                } else if ((Timestamp.valueOf(LocalDateTime.parse(arrTimes.get(0))).getTime()
                        - Timestamp.valueOf(LocalDateTime.parse(container.getDepartureDate())).getTime()) < 900_000) {
                    errors.rejectValue("sideArrivalTimes", "Invalid.route.duration");
                }

            }  else if(!isEnteredArrivalTimeValid(CollectionUtils.getLast(resultArrTimes), arrTimes.get(0),
                    CollectionUtils.getLast(resultStops))) {
                errors.rejectValue("sideArrivalTimes", "Invalid.routeTime.2");

            } else if (LocalDateTime.parse(arrTimes.get(0))
                    .compareTo(LocalDateTime.parse(container.getDeclaredArrivalDate())) > 0) {
                errors.rejectValue("sideArrivalTimes", "TimeIsMoreThanMain");
            }


        } else errors.rejectValue("sideArrivalTimes", "Empty.time");


        List<String> stopsList = routeDTO.getStops();
        if (CollectionUtils.isEmpty(stopsList)) {
            errors.rejectValue("stops", "Empty.stop.section");
        }
    }

    private boolean isEnteredArrivalTimeValid(String arrivalTime, String arrivalTimeToCompare, String stopDuration){
         return (Timestamp.valueOf(LocalDateTime.parse(arrivalTimeToCompare)).getTime() -
                Timestamp.valueOf(LocalDateTime.parse(arrivalTime)
                        .plusMinutes(Long.parseLong(stopDuration))).getTime()) >= 900_000;


    }
}
