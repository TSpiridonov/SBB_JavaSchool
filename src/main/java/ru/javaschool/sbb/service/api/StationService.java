package ru.javaschool.sbb.service.api;



import ru.javaschool.sbb.DTO.StationDTO;
import ru.javaschool.sbb.entity.Station;

import java.util.List;

public interface StationService {

    List<StationDTO> getAllStationsDTO();

    StationDTO getStationDTOById(int id);

    StationDTO findStationDTOByTitle(String title);

    void addStation(StationDTO stationDTO);

    void editStation(StationDTO stationDTO);

    void deleteStation(StationDTO stationDTO);

}
