package com.polyauto.entities;

import java.io.Serializable;
import java.sql.Date;

public class Bookings implements Serializable {
    private int idBooking;
    private byte status;
    private Date dateDown;
    private Date dateUp;

    public int getIdBooking() {
        return idBooking;
    }

    public void setIdBooking(int idBooking) {
        this.idBooking = idBooking;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Date getDateDown() {
        return dateDown;
    }

    public void setDateDown(Date dateDown) {
        this.dateDown = dateDown;
    }

    public Date getDateUp() {
        return dateUp;
    }

    public void setDateUp(Date dateUp) {
        this.dateUp = dateUp;
    }
}