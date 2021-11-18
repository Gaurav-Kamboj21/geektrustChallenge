package com.makespace.Entities;
public class Room {
    private Long roomId;
    private String roomName;
    private int roomCapacity;
    public Long getRoomId() {
        return roomId;
    }
    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }
    public String getRoomName() {
        return roomName;
    }
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    public int getRoomCapacity() {
        return roomCapacity;
    }
    public void setRoomCapacity(int roomCapacity) {
        this.roomCapacity = roomCapacity;
    }
}
