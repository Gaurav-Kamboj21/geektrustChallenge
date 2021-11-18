package com.makespace.Repositories;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.makespace.Entities.Booking;
import com.makespace.Entities.Room;
import com.makespace.Utils.DateUtils;

public class BookingRepositoryImpl implements BookingRepository{
    private static final Map<Long,Booking>bookingMap = new HashMap<>();
    private static Long bookingId = 0l; 
    @Override
    public List<Room> getBookedRooms() {
        List<Room>bookedRooms = new ArrayList<>();
        for(Booking booking : bookingMap.values()){
              bookedRooms.add(booking.getBookedRoom());  
        }
        return bookedRooms;
    }
    @Override
    public void bookRoom(Room room, Date startTime, Date endTime) {
        Booking booking  = new Booking();
        booking.setBookingId(++bookingId);
        booking.setBookedRoom(room);
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        bookingMap.put(bookingId,booking);
    }
    @Override
    public List<Booking> getAllBookingsByRequestedRoom(Room room) {
        List<Booking>allBookingByRoom = new ArrayList<>();
        Date date = new Date();
        for(Booking booking : bookingMap.values()){
           if(room.getRoomName().equalsIgnoreCase(booking.getBookedRoom().getRoomName()) 
                                 && DateUtils.isSameDay(date,booking.getStartTime())){
                allBookingByRoom.add(booking);
            } 
        }
       return allBookingByRoom;
    }
    @Override
    public List<Booking> getAllBookingByCurrentRequestedDate(Date date) {
        List<Booking>bookings = new ArrayList<>();
        for(Booking booking : bookingMap.values()){
            if(DateUtils.isSameDay(date, booking.getStartTime())){
                bookings.add(booking);
            }
        }
        return bookings;
    }
    
}
