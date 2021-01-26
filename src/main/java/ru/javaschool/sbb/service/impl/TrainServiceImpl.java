package ru.javaschool.sbb.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javaschool.sbb.DAO.api.TrainDAO;
import ru.javaschool.sbb.DTO.TrainDTO;
import ru.javaschool.sbb.entity.Train;
import ru.javaschool.sbb.mapper.TrainMapper;
import ru.javaschool.sbb.service.api.TrainService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainServiceImpl implements TrainService {

    private final TrainDAO trainRepository;
    private final TrainMapper trainMapper;


    @Override
    @Transactional
    public List<TrainDTO> getAllTrainsDTO() {
        List<Train> trains = trainRepository.getAllTrains();
        return trainMapper.toDTOList(trains);
    }

    @Override
    @Transactional
    public TrainDTO getTrainDTOById(int id) {
        Train train = trainRepository.getTrain(id);
        return trainMapper.toDTO(train);
    }

    @Override
    @Transactional
    public TrainDTO findTrainByName(String trainName) {
        Train train = trainRepository.getTrainByNumber(trainName);
        return trainMapper.toDTO(train);
    }

    @Override
    @Transactional
    public void add(TrainDTO trainDTO) {
        Train train = trainMapper.toEntity(trainDTO);
        trainRepository.addTrain(train);
    }

    @Override
    @Transactional
    public void edit(TrainDTO trainDTO) {
        Train train = trainMapper.toEntity(trainDTO);
        trainRepository.editTrain(train);
    }

    @Override
    @Transactional
    public void delete(TrainDTO trainDTO) {
        Train train = trainMapper.toEntity(trainDTO);
        trainRepository.deleteTrain(train);
    }

}
