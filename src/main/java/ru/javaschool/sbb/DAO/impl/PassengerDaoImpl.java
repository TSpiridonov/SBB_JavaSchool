package ru.javaschool.sbb.DAO.impl;

import jakarta.validation.constraints.Past;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.sbb.DAO.api.PassengerDAO;
import ru.javaschool.sbb.entity.Passenger;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Repository
public class PassengerDaoImpl implements PassengerDAO {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    public Passenger getPassenger(int id) {
        return entityManager.find(Passenger.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Passenger> getAllPassengers() {
        Query query = entityManager.createQuery("SELECT p FROM Passenger p", Passenger.class);
        return query.getResultList();
    }

    @Override
    public void addPassenger(Passenger passenger) {
        entityManager.persist(entityManager.merge(passenger));
    }

    @Override
    public void editPassenger(Passenger passenger) {
        entityManager.merge(passenger);
    }

    @Override
    public void deletePassenger(Passenger passenger) {
        entityManager.remove(entityManager.merge(passenger));
    }

    @Override
    public Passenger getPassengerByPersonalData(String name, String surname, @Past Date birthDate) {
        Query query = entityManager.createQuery("select p from Passenger p where p.name = :firstName " +
                "and p.surname = :lastName " +
                "and p.birthDate = :birthDate")
                .setParameter("firstName", name)
                .setParameter("lastName", surname)
                .setParameter("birthDate", birthDate);
        Passenger passenger = null;
        try {
            passenger = (Passenger) query.getSingleResult();
        } catch (NoResultException ignored) {
        }
        return passenger;
    }

 }