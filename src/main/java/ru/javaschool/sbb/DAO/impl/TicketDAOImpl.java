package ru.javaschool.sbb.DAO.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javaschool.sbb.DAO.api.TicketDAO;
import ru.javaschool.sbb.entity.Passenger;
import ru.javaschool.sbb.entity.Ticket;
import ru.javaschool.sbb.entity.Train;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public class TicketDAOImpl implements TicketDAO {

    @Autowired
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Ticket getTicket(int id) {
        return entityManager.find(Ticket.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Ticket> getAllTickets() {
        Query query = entityManager.createQuery("SELECT t FROM Ticket t");
        return query.getResultList();
    }

    @Override
    public void addTicket(Ticket ticket) {
        entityManager.persist(entityManager.merge(ticket));
    }

    @Override
    public void editTicket(Ticket ticket) {
        entityManager.merge(ticket);
    }

    @Override
    public void deleteTicket(Ticket ticket) {
        entityManager.remove(entityManager.merge(ticket));
    }

    @Override
    public BigInteger getTakenSeatsCount(int trainId, int tripId, Timestamp departureTime, Timestamp arrivalTime) {
        Query query = entityManager
                .createNativeQuery("select count(*) from ticket where train_id=? and trip_id=? " +
                        "and valid=1 and arrival_time >= ? and departure_time <= ? for update");
        query.setParameter(1, trainId);
        query.setParameter(2, tripId);
        query.setParameter(3, departureTime);
        query.setParameter(4, arrivalTime);

        return (BigInteger) query.getSingleResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object[]> getAllTicketsByUserId(int userId) {
        Query query = entityManager
                .createNativeQuery("select ticket.id, tr.name as tname, p.firstname, p.lastname, p.birthdate, " +
                        "st1.name as stfname, st2.name as sttname, ticket.departure_time, ticket.arrival_time, " +
                        "ticket.valid FROM ticket " +
                        "inner join train tr on tr.id = ticket.train_id " +
                        "inner join passenger p on p.id = ticket.passenger_id " +
                        "inner join station st1 on st1.id = ticket.station_from_id " +
                        "inner join station st2 on st2.id = ticket.station_to_id " +
                        "where ticket.user_id=?;");
        query.setParameter(1, userId);
        return query.getResultList();
    }

}