package ru.javaschool.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.sbb.DAO.api.StationDAO;
import ru.javaschool.sbb.DTO.StationDTO;
import ru.javaschool.sbb.entity.Station;
import ru.javaschool.sbb.mapper.StationMapper;
import ru.javaschool.sbb.service.api.StationService;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StationServiceImpl implements StationService {

    private final StationDAO stationDAO;
    private final StationMapper stationMapper;


    @Override
    @Transactional
    public List<StationDTO> getAllStationsDTO() {
        List<Station> stations = stationDAO.getAllStations();
        return stationMapper.toDTOList(stations);
    }


    @Override
    @Transactional
    public StationDTO getStationDTOById(int id) {
        return stationMapper.toDTO(stationDAO.getStation(id));
    }


    @Override
    @Transactional
    public StationDTO findStationDTOByTitle(String title) {
        Station station = stationDAO.getStationByTitle(title);
        return stationMapper.toDTO(station);
    }

    @Override
    public void addStation(StationDTO stationDTO) {
        Station station = stationMapper.toEntity(stationDTO);
        stationDAO.addStation(station);
    }

    @Override
    public void editStation(StationDTO stationDTO) {
        Station station = stationMapper.toEntity(stationDTO);
        stationDAO.editStation(station);
    }

    @Override
    public void deleteStation(StationDTO stationDTO) {
        Station station = stationMapper.toEntity(stationDTO);
        stationDAO.deleteStation(station);
    }

}
