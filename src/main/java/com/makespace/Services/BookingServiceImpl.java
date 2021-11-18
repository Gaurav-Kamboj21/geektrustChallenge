package com.makespace.Services;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import com.makespace.Entities.Room;
import com.makespace.Exceptions.BookingTimeOverlappingAcrossDaysException;
import com.makespace.Exceptions.NoVacantRoomException;
import com.makespace.Repositories.BookingRepository;
import com.makespace.Utils.DateUtils;

public class BookingServiceImpl implements BookingService{

    // private final RoomRepository roomRepository;
    private final BookingRepository bookingRepository;
    private final RoomService roomService;
    
    public BookingServiceImpl(BookingRepository bookingRepository,RoomService roomService){
        this.bookingRepository = bookingRepository;
        this.roomService = roomService;
    }

    @Override
    public String bookMeetingRoom(Date startTime, Date endTime,int capacity) throws BookingTimeOverlappingAcrossDaysException,NoVacantRoomException{
        Calendar cal1 = Calendar.getInstance();   
        Calendar cal2 = Calendar.getInstance();
        if(DateUtils.checkIfMettingInBufferTime(startTime, endTime)){
            throw new NoVacantRoomException("no vacant room");
        }
        cal1.setTime(startTime);   
        cal2.setTime(endTime); 
        if (cal1.after(cal2)){
            throw new BookingTimeOverlappingAcrossDaysException("Booking time is overlapping across days");
        }  
        List<Room>vacantRooms = roomService.showVacantRooms(startTime, endTime);
        Comparator<Room> NAME = (Room o1, Room o2) -> o1.getRoomCapacity() - o2.getRoomCapacity();
        Collections.sort(vacantRooms,NAME);
        Room room = null;
        for(Room vacantRoom : vacantRooms){
            if(vacantRoom.getRoomCapacity() >= capacity){
                room = vacantRoom;
                break;
            }
        }
        if(room == null){
            throw new NoVacantRoomException("no vacant Room found");
        }
        bookingRepository.bookRoom(room, startTime, endTime);
        return room.getRoomName();
    }
}
