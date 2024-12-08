package com.github.ana.deliverymanagement.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
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


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,optional= false)
    @JoinColumn(name = "users_id")
    private Users users;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,optional= false)
    @JoinColumn(name = "users_deliver_id")
    private Users users_deliver;

    @JsonIgnore
    @OneToMany(mappedBy = "packet", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<PacketStatus> packetstatus = new ArrayList<>();


}

