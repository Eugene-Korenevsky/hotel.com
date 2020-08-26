package com.company.model.order;

import java.util.Date;

public class CurrentDate {
    private Date date;

    public long getCurrentDate() {
        date = new Date();
        return date.getTime();
    }

}
