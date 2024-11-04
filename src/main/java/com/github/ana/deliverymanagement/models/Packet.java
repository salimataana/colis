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
    private Double weight;
    private String address_packet;
    private Integer code_postal;
    private Date date_creation;

    @Column(nullable = true)
    private Date date_arrival;



    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

    @ManyToOne
    @JoinColumn(name = "users_deliver_id")
    private Users users_deliver;


    @OneToMany(mappedBy = "packet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PacketStatus> packetstatus = new ArrayList<>();


}

