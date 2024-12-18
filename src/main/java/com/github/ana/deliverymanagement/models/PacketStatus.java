package com.github.ana.deliverymanagement.models;
import com.fasterxml.jackson.annotation.JsonIgnore;
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



    public Date created_at;
    @ManyToOne(fetch = FetchType.LAZY,optional= false)
    @JoinColumn(name = "packet_id")
    private Packet packet;

}
