package com.makespace.Repositories;

import java.util.Date;
import java.util.List;

import com.makespace.Entities.Booking;
import com.makespace.Entities.Room;

public interface BookingRepository {
    List<Room>getBookedRooms();
    void bookRoom(Room room,Date startTime,Date endTime);
    List<Booking>getAllBookingsByRequestedRoom(Room room);
    List<Booking>getAllBookingByCurrentRequestedDate(Date date);
}
