package ru.javaschool.sbb.DAO.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.sbb.DAO.api.StationDAO;
import ru.javaschool.sbb.entity.Station;
import ru.javaschool.sbb.entity.Train;

import javax.persistence.*;
import java.util.List;

@Repository
public class StationDAOImpl implements StationDAO {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Station getStation(int id) {
        return entityManager.find(Station.class, id);
    }

    @Override
    public Station getStationByTitle(String title) {
        Station station;
        try {
            station = (Station) entityManager.createQuery("SELECT s FROM Station s " +
                    "WHERE s.name = :name").setParameter("name", title).getSingleResult();
        } catch (NoResultException e) {
            station = null;
        }
        return station;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Station> getAllStations() {
        Query query = entityManager.createQuery("SELECT s FROM Station s ORDER BY s.name");
        return query.getResultList();
    }

    @Override
    public void addStation(Station station) {
        entityManager.persist(station);
    }

    @Override
    public void editStation(Station station) {
        entityManager.merge(station);
    }

    @Override
    public void deleteStation(Station station) {
        entityManager.remove(entityManager.merge(station));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Station> getStationsByTrain(Train train) {
        Query query = entityManager.createQuery("SELECT DISTINCT s.station FROM Schedule s WHERE t.train = :train");
        query.setParameter("train", train);
        return query.getResultList();
    }
}