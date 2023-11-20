package com.bkj.demo.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonFormat
//@Table
@Entity
public class Workorder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workorderid;
    private String workordernumber;
    private String originatorcode;
    private String receivercode;
    private Date workorderdate;
    private String vessel;
    private String voyage;
    private Date eta;



    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "workorderid")
    private Set<Stop> stops = new HashSet<Stop>(0);



    public Workorder(Long workorderid, String workordernumber, String originatorcode, String receivercode, Date workorderdate, Set<Stop> stops) {
        this.workorderid = workorderid;
        this.workordernumber = workordernumber;
        this.originatorcode = originatorcode;
        this.receivercode = receivercode;
        this.workorderdate = workorderdate;
        this.stops = stops;
    }




}
