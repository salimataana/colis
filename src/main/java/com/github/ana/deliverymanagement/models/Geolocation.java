package com.github.ana.deliverymanagement.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import java.util.Date;


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
    private String description;


   // @CreatedDate
   // @Column(name = "created_at", nullable = false, updatable = false)
   // private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User users;
}
