package com.makespace.Commands;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
// import java.util.Calendar;
import java.util.List;

import com.makespace.Entities.Room;
import com.makespace.Exceptions.BookingTimeOverlappingAcrossDaysException;
// import com.makespace.Exceptions.IncorrectInputException;
import com.makespace.Exceptions.NoVacantRoomException;
import com.makespace.Services.RoomService;
import com.makespace.Utils.DateUtils;

public class ShowVacantRoomsCommand implements Command{
    private final RoomService roomService;
    public ShowVacantRoomsCommand(RoomService roomService){
        this.roomService = roomService;
    }

    @Override
    public void execute(List<String> tokens) { 
        if(tokens.size() != 3){
            System.out.println("INCORRECT_INPUT");
            return;
        }
        String startTime  = tokens.get(1);
        String endTime = tokens.get(2);
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
            List<Room>vacantRooms = new ArrayList<>();
            vacantRooms = roomService.showVacantRooms(startDate,endDate);
            for(Room vacantRoom : vacantRooms){
                System.out.print(vacantRoom.getRoomName() + " ");
            }
            System.out.println();
        }catch(NoVacantRoomException e){
            System.out.println("NO_VACANT_ROOM");
        }catch(BookingTimeOverlappingAcrossDaysException e){
            System.out.println("INCORRECT_INPUT");
        }
    }
    
}
