package com.github.ana.deliverymanagement.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Geolocation {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private Double latitude;
    private Double longitude;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User users;
}
