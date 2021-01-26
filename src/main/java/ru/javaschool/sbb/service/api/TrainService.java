package ru.javaschool.sbb.service.api;



import ru.javaschool.sbb.DTO.TrainDTO;

import java.util.List;

public interface TrainService {

    void add(TrainDTO trainDTO);

    void edit(TrainDTO trainDTO);

    void delete(TrainDTO trainDTO);

    List<TrainDTO> getAllTrainsDTO();

    TrainDTO getTrainDTOById(int id);

    TrainDTO findTrainByName(String trainName);



}
