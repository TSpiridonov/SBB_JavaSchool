package ru.javaschool.sbb.DAO.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.sbb.DAO.api.TripDAO;
import ru.javaschool.sbb.DTO.StationDTO;
import ru.javaschool.sbb.entity.Station;
import ru.javaschool.sbb.entity.Trip;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TripDAOImpl implements TripDAO {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Trip getTrip(int id) {
        return entityManager.find(Trip.class, id);
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Trip> getAllTrips() {
        return entityManager.createQuery("SELECT t FROM Trip t").getResultList();
    }

    @Override
    public void addTrip(Trip trip) {
        entityManager.persist(entityManager.merge(trip));
    }

    @Override
    public void editTrip(Trip trip) {
        entityManager.merge(trip);
    }

    @Override
    public void deleteTrip(Trip trip) {
        entityManager.remove(entityManager.merge(trip));
    }

    @Override
    @SuppressWarnings(value = "unchecked")
    public List<Trip> getTripsByTrainId(int trainId) {
        Query query = entityManager.createQuery("select t from Trip t where t.train.id = :trainId");
        query.setParameter("trainId", trainId);
        return query.getResultList();
    }


}

