package com.bkj.demo.Utilities;


import com.bkj.demo.Entity.Driver;
import com.bkj.demo.Entity.Workorder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RequestData {

    private List<Driver> drivers;
    private List<Workorder> workorders;

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    public void setWorkorders(List<Workorder> workorders) {
        this.workorders = workorders;
    }

}

