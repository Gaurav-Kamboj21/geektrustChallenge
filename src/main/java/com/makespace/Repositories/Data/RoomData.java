package com.makespace.Repositories.Data;

import com.makespace.Repositories.RoomRepository;

public class RoomData implements Data{
    
    private final RoomRepository roomRepository;
    public RoomData(RoomRepository roomRepository){
        this.roomRepository = roomRepository;
    }
    @Override
    public void loadData() {
        roomRepository.addRoom(1L,"C-Cave", 3);
        roomRepository.addRoom(2L,"D-Tower", 7);
        roomRepository.addRoom(3L, "G-Mansion",20);
    }
    
}
