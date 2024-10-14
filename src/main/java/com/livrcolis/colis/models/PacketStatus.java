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
public class PacketStatus {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private String desciption;

    @ManyToOne
    @JoinColumn(name = "packet_id")
    private Packet packet;

}
