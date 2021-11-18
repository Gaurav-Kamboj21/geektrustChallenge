package com.makespace.Configs;

import com.makespace.Commands.BookRoomCommand;
import com.makespace.Commands.Command;
import com.makespace.Commands.CommandInvoker;
import com.makespace.Commands.ShowVacantRoomsCommand;
import com.makespace.Repositories.*;
import com.makespace.Repositories.Data.DataLoader;
import com.makespace.Repositories.Data.RoomData;
import com.makespace.Services.*;
public class ApplicationConfig {
  private  final BookingRepository bookingRepository = new BookingRepositoryImpl();
  private final RoomRepository roomRepository = new RoomRepositoryImpl();
  private final RoomService roomService = new RoomServiceImpl(bookingRepository, roomRepository);
  private final BookingService bookingService = new BookingServiceImpl(bookingRepository, roomService);

  CommandInvoker commandInvoker = new CommandInvoker();
  DataLoader dataLoader = new DataLoader();
  private final Command bookRoomCommad = new BookRoomCommand(bookingService);
  private final Command showVacantRooms = new ShowVacantRoomsCommand(roomService);

  public CommandInvoker getCommandInvoker(){
    commandInvoker.register("BOOK",bookRoomCommad);
    commandInvoker.register("VACANCY",showVacantRooms);
    return commandInvoker;
}
public DataLoader getDataLoader(){
     dataLoader.register("ROOM-DATA",new RoomData(roomRepository));
    return dataLoader;
}



}
