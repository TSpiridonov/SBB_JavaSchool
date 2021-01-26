package ru.javaschool.sbb.DAO.api;



import ru.javaschool.sbb.entity.Station;
import ru.javaschool.sbb.entity.Train;

import java.util.List;

public interface StationDAO {

    Station getStation(int id);

   Station getStationByTitle(String title);

    List<Station> getAllStations();

    void addStation(Station station);

    void editStation(Station station);

    void deleteStation(Station station);

    List<Station> getStationsByTrain(Train train);
}
