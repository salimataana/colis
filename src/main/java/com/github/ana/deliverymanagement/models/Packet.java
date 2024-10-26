package com.github.ana.deliverymanagement.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity

public class Packet {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private String address_packet;
    private Date date_depart;
    private Date date_arrival;
    private Double weight;
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User users;


    @OneToMany(mappedBy = "packet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PacketStatus> packetstatus = new ArrayList<>();


}

