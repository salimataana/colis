package com.github.ana.deliverymanagement.models;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
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
    private Date date_depart;

    @Column(nullable = true)
    private Date date_arrival;



   // @CreatedDate
   // @Column(name = "created_at", nullable = false, updatable = false)
   // private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User users;

    @ManyToOne
    @JoinColumn(name = "users_deliver_id")
    private User users_deliver;


    @OneToMany(mappedBy = "packet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PacketStatus> packetstatus = new ArrayList<>();


}

