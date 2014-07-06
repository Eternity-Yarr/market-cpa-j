package org.lutra.cpa.model;

import java.util.Date;

public class Dates {

    Date fromDate;
    Date toDate;

    public Dates setFromDate(Date fromDate)
    {
        this.fromDate = fromDate;

        return this;
    }
    public Dates setToDate(Date toDate)
    {
        this.toDate = toDate;

        return this;
    }
}
