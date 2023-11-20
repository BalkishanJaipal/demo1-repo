package com.bkj.demo.Utilities;

import lombok.Data;

import java.util.Date;
@Data
public class DateHelper {
    private Date currentDate;

    public DateHelper(Date currentDate) {
        this.currentDate = currentDate;
    }

}
