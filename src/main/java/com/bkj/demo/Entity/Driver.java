package com.bkj.demo.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="drivers")
@Entity

public class Driver {
     @Id
     @GeneratedValue()
    Long driverId;
    String driverName;
    Integer age;
    Integer dateOfJoining;
    String driverOfficeCode;
    String availableLocationName;
    String availableAddress1;
    String availableCity;
    String availableState;
    String availablePostalcode;
    String availableCountry;
    Double basicPay;
    Boolean isPremiumDriver;


    //Set<DriverAvailableSlot> driverAvailableSlots;
    public Driver(String driverName, String availableCity, String availablePostalcode, Double basicPay,Integer age) {
        this.driverName = driverName;
        this.age = age;
        this.availableCity = availableCity;
        this.availablePostalcode = availablePostalcode;
        this.basicPay = basicPay;
    }
}
