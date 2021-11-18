package com.makespace.Repositories;

import java.util.List;

import com.makespace.Entities.Room;
import com.makespace.Exceptions.RoomNotFoundException;

public interface RoomRepository {
    Room getRoomById(Long roomId) throws RoomNotFoundException;
    Room getRoomByName(String name) throws RoomNotFoundException;
    void addRoom(Long id,String name,int capacity);
    List<Room>getAllRooms();

}
