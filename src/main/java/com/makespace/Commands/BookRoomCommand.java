package com.makespace.Commands;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.makespace.Exceptions.BookingTimeOverlappingAcrossDaysException;
import com.makespace.Exceptions.NoVacantRoomException;
import com.makespace.Services.BookingService;
import com.makespace.Utils.DateUtils;

public class BookRoomCommand implements Command{
    private final BookingService bookingService;
    public BookRoomCommand(BookingService bookingService){
        this.bookingService = bookingService;
    }

    @Override
    public void execute(List<String> tokens) {
        if(tokens.size() != 4){
            System.out.println("INCORRECT_INPUT");
            return;
        }
        String startTime  = tokens.get(1);
        String endTime = tokens.get(2);
        int capacity = Integer.parseInt(tokens.get(3));
        String startTimeHourMinute[] = startTime.split(":");
        String endTimeHourMinute[] = endTime.split(":");

        if(startTimeHourMinute.length != 2 || endTimeHourMinute.length != 2) {
            System.out.println("INCORRECT_INPUT");
            return;
        }
        Date startDate = new Date();
        Date endDate = new Date();
        startDate = DateUtils.getExactDateWithTime(startTimeHourMinute);
        endDate = DateUtils.getExactDateWithTime(endTimeHourMinute);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(startDate);
        if(calendar.get(Calendar.MINUTE) % 15 != 0){
            System.out.println("INCORRECT_INPUT");
            return;
        }
        calendar.setTime(endDate);
        if(calendar.get(Calendar.MINUTE) % 15 != 0){
            System.out.println("INCORRECT_INPUT");
            return;
        }
        try{
            String roomName =  bookingService.bookMeetingRoom(startDate, endDate,capacity);
            System.out.println(roomName);
        }catch(NoVacantRoomException e){

            System.out.println("NO_VACANT_ROOM");
        }catch(BookingTimeOverlappingAcrossDaysException e){

            System.out.println("INCORRECT_INPUT");
        }

        }
    
}
