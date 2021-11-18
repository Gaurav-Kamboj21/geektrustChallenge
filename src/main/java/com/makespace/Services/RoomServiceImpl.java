package com.makespace.Services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.makespace.Entities.Booking;
import com.makespace.Entities.Room;
import com.makespace.Exceptions.BookingTimeOverlappingAcrossDaysException;
import com.makespace.Exceptions.NoVacantRoomException;
import com.makespace.Repositories.BookingRepository;
import com.makespace.Repositories.RoomRepository;
import com.makespace.Utils.DateUtils;

public class RoomServiceImpl implements RoomService {

    private final BookingRepository bookingRepository;
    private final RoomRepository roomRepository;

    public RoomServiceImpl(BookingRepository bookingRepository,RoomRepository roomRepository){
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }
    @Override
    public List<Room> showVacantRooms(Date startTime,Date endTime) throws BookingTimeOverlappingAcrossDaysException{
        Calendar cal1 = Calendar.getInstance();   
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(startTime);   
        cal2.setTime(endTime); 
        if (cal1.after(cal2)){
            throw new BookingTimeOverlappingAcrossDaysException("Booking time is overlapping across days");
        }  
        Date todaysDate = new Date();
        if(DateUtils.checkIfMettingInBufferTime(startTime, endTime)){
            throw new NoVacantRoomException("no vacant room");
        }
        List<Booking>allBookingsForCurrentDay = bookingRepository.getAllBookingByCurrentRequestedDate(todaysDate);
        Set<String>roomNameSet = new HashSet<>();
        
        for(Booking booking : allBookingsForCurrentDay){
            if(DateUtils.isOverlapping(startTime, endTime, booking.getStartTime(),
                                                           booking.getEndTime()) || 
               DateUtils.isOverlapping(booking.getStartTime(),booking.getEndTime()
                                                           ,startTime,endTime)){
                roomNameSet.add(booking.getBookedRoom().getRoomName());
            }
        }
        List<Room>allRooms = roomRepository.getAllRooms();
        List<Room>vacantRooms = new ArrayList<>();
        for(Room room : allRooms){
            if(!roomNameSet.contains(room.getRoomName())){
                vacantRooms.add(room);
            }
        }
        if(vacantRooms.size() == 0)
           throw new NoVacantRoomException("no vacant room found");
        
        return vacantRooms;
    }
    
}
