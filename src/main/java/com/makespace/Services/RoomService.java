package com.makespace.Services;

import java.util.Date;
import java.util.List;

import com.makespace.Entities.Room;

public interface RoomService {
    List<Room>showVacantRooms(Date startTime,Date endTime);
} 
