package com.makespace.Repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.makespace.Entities.Room;
import com.makespace.Exceptions.RoomNotFoundException;

public class RoomRepositoryImpl implements RoomRepository{
    private static final Map<Long,Room>roomMap = new HashMap<>(); 
    @Override
    public Room getRoomById(Long roomId) throws RoomNotFoundException {
        Room room = null;
        if(roomMap.containsKey(roomId)){
            throw new RoomNotFoundException("Requested room not found");
        }
        room = roomMap.get(roomId);
        return room;
    }

    @Override
    public Room getRoomByName(String name) throws RoomNotFoundException{
        Room room =  new Room();
        boolean roomFoundByRequestedName = false;
        for(Room currentRoom : roomMap.values()){
            if(currentRoom.getRoomName().equalsIgnoreCase(name)){
                room = currentRoom;
                roomFoundByRequestedName = true;
            }
        } 
        if(!roomFoundByRequestedName) throw new RoomNotFoundException("requested Room not found");
        return room;
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room>allRooms = new ArrayList<>();
        allRooms.addAll(roomMap.values());
        return allRooms;
    }

    @Override
    public void addRoom(Long id, String name, int capacity) {
       Room room = new Room();
       room.setRoomId(id);
       room.setRoomCapacity(capacity);
       room.setRoomName(name);
       roomMap.put(id,room);    
    }
    
}
