package ru.javaschool.sbb.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "station", schema = "sbb")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Station implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name = "title")
    private String title;


}