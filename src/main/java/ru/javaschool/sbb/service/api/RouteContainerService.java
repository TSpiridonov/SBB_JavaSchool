package ru.javaschool.sbb.service.api;

import ru.javaschool.sbb.DTO.RouteDTO;

public interface RouteContainerService {


    void setInitialInfo(RouteDTO routeDTO);


    void setSegmentsInfo(RouteDTO routeDTO);


    void updateSegmentsInfo(RouteDTO routeDTO);


    void deleteLastChange();


    void truncateContainer();
}