package ru.javaschool.sbb.DAO.api;

import ru.javaschool.sbb.entity.Train;

import java.util.List;

public interface TrainDAO {

    Train getTrain(int id);

    Train getTrainByNumber(String number);

    List<Train> getAllTrains();

    void addTrain(Train train);

    void editTrain(Train train);

    void deleteTrain(Train train);

}
