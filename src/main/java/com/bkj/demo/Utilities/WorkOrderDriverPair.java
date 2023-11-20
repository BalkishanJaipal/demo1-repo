package com.bkj.demo.Utilities;

import com.bkj.demo.Entity.Driver;
import com.bkj.demo.Entity.Workorder;

import java.util.List;

public class WorkOrderDriverPair {

    private Workorder workorder;
    private List<Driver> sortedDrivers;

    public void WorkorderDriverPair(Workorder workorder, List<Driver> sortedDrivers) {
        this.workorder = workorder;
        this.sortedDrivers = sortedDrivers;
    }

    public Workorder getWorkorder() {
        return workorder;
    }

    public List<Driver> getSortedDrivers() {
        return sortedDrivers;
    }
}
