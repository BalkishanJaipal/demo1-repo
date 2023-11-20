package com.bkj.demo.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
//@Table
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {

    @Id
    @GeneratedValue
    private Long appointmentid;
    @ManyToOne // This relationship signifies that multiple appointments can be associated with a single stop.
    private Stop stop;
    private LocalDateTime scheduledtimestart;
    private LocalDateTime scheduledtimeend;
    private String scheduledtimeform;
    private Date actualtime;


    public Appointment(LocalDateTime scheduledtimestart) {
        this.scheduledtimestart =scheduledtimestart;
    }
}