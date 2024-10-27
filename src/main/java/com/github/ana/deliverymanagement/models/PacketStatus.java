package com.github.ana.deliverymanagement.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import org.springframework.data.annotation.CreatedDate;

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
    private String description;


    //@CreatedDate
   // @Column(name = "created_at", nullable = false, updatable = false)
   // private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "packet_id")
    private Packet packet;

}
