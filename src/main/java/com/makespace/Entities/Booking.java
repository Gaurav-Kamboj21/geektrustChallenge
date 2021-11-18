package com.makespace.Entities;

import java.util.Date;

public class Booking {
    private Long bookingId;
    private Room bookedRoom;
    private Date startTime;
    private Date endTime;
    public Long getBookingId() {
        return bookingId;
    }
    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }
    public Room getBookedRoom() {
        return bookedRoom;
    }
    public void setBookedRoom(Room bookedRoom) {
        this.bookedRoom = bookedRoom;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
}
