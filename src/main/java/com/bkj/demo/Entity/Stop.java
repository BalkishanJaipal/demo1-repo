package com.bkj.demo.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Table(name ="Stops")
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stopid;

    @ManyToOne
    @JoinColumn(name = "workorderid")
    //This relationship signifies that multiple stops can be associated with a single stop workorder.
    private Workorder workorder;

    private Long stopnumber;
    private String stoptype;
    private String facilitycode;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String postalcode;
    private String country;
    private String telephone;
    private String fax;
    private String comments;
    private String contactname;
    private String contactphone;
    private String contactEmail;
    private Double latitude;
    private Double longitude;
    private String appointmentRequired;

    @OneToMany //This property represents a set of appointments associated with the one stop
    private Set<Appointment> appointments = new HashSet<Appointment>(0);


    public Stop(String city , String postalcode ,Set<Appointment> appointments)
    {
        this.city = city;
        this.postalcode = postalcode;
        this.appointments = appointments;
    }
}
