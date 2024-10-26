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

public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private String email;
    private String address_user;
    private String phoneNumber;
    private Date createdAt;


    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Role> roles = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Packet> packets = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Geolocation> geolocations = new ArrayList<>();

}
