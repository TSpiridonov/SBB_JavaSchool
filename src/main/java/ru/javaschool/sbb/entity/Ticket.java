package ru.javaschool.sbb.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name = "ticket", schema = "sbb")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Ticket implements Serializable {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "station_from_id")
    private Station stationFrom;

    @ManyToOne
    @JoinColumn(name = "station_to_id")
    private Station stationTo;

    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "departure_time")
    private Timestamp departureTime;

    @DateTimeFormat(pattern = "HH:mm")
    @Column(name = "arrival_time")
    private Timestamp arrivalTime;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}