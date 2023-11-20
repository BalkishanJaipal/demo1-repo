package com.bkj.demo.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Time;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DriverAvailableSlot {

    private Long driverId;
    private Time availabilityStartTime;
    private Time availabilityEndTime;
    private String day;

}
