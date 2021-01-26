package ru.javaschool.sbb.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.CollectionUtils;
import org.springframework.stereotype.Service;
import ru.javaschool.sbb.DTO.RouteContainer;
import ru.javaschool.sbb.DTO.RouteDTO;
import ru.javaschool.sbb.DTO.StationDTO;
import ru.javaschool.sbb.DTO.TrainDTO;
import ru.javaschool.sbb.service.api.RouteContainerService;
import ru.javaschool.sbb.service.api.StationService;
import ru.javaschool.sbb.service.api.TrainService;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteContainerServiceImpl implements RouteContainerService {

    private final RouteContainer routeContainer;
    private final StationService stationService;
    private final TrainService trainService;

    @Override
    public void setInitialInfo(RouteDTO routeDTO) {
        TrainDTO trainDTO = trainService.getTrainDTOById(routeDTO.getTrainId());
        StationDTO stationDTO = stationService.getStationDTOById(routeDTO.getDepartureStationId());
        routeContainer.setTrainDTO(trainDTO);
        routeContainer.setDepartureStation(stationDTO);
        routeContainer.setDepartureDate(routeDTO.getDepartureDate());
        routeContainer.setDeclaredArrivalDate(routeDTO.getDeclaredArrivalDate());
    }

    @Override
    public void setSegmentsInfo(RouteDTO routeDTO) {
        routeContainer.setSideArrivalTimes(routeDTO.getSideArrivalTimes());
        routeContainer.setSideStations(routeDTO.getSideStations()
                .stream()
                .map(Integer::parseInt)
                .map(s -> stationService.getStationDTOById(s))
                .collect(Collectors.toList()));
        routeContainer.setStops(routeDTO.getStops());
    }

    @Override
    public void updateSegmentsInfo(RouteDTO routeDTO) {

//        StationDTO stationDTO = stationService
//                .getStationDTOById(Integer.parseInt(CollectionUtils.getLast(routeDTO.getSideStations())));
//
//        routeContainer.getSideStations().add(stationDTO);
//        routeContainer.getSideArrivalTimes().add(CollectionUtils.getLast(routeDTO.getSideArrivalTimes()));
//        routeContainer.getStops().add(CollectionUtils.getLast(routeDTO.getStops()));
    }

    @Override
    public void deleteLastChange() {
//        CollectionUtils.removeLast(routeContainer.getSideStations());
//        CollectionUtils.removeLast(routeContainer.getSideArrivalTimes());
//        CollectionUtils.removeLast(routeContainer.getStops());
    }

    @Override
    public void truncateContainer() {
//        routeContainer.setTrainDTO(null);
//        routeContainer.setDepartureStation(null);
//        routeContainer.setDepartureDate(null);
//        routeContainer.setDeclaredArrivalDate(null);
//        routeContainer.setSideStations(null);
//        routeContainer.setSideArrivalTimes(null);
//        routeContainer.setStops(null);
    }


}
