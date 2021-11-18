package com.makespace.Services;

import java.util.Date;


public interface BookingService {
    String bookMeetingRoom(Date startTime,Date endTime,int capacity);
}
