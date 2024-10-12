package com.livrcolis.colis.models;

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
public class EtatColis {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private String desciption;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colis_id")
    private Colis colis;

}
