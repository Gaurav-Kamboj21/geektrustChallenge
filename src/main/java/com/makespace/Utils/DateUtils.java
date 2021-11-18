package com.makespace.Utils;

// import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
// import java.util.List;

public class DateUtils {
    public static boolean isSameDay(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }
    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if (cal1 == null || cal2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
    }
    public static boolean isOverlapping(Date start1, Date end1, Date start2, Date end2) {
        return !start1.after(end2) && !start2.after(end1);
    }

    public static Date getExactDateWithTime(String timeHourMinute[]){

        Date date = new Date();
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(date);
        int timeHourInteger = 
        timeHourMinute[0].charAt(0) == '0' ? 0 : Integer.parseInt(timeHourMinute[0].charAt(0)+"");
        timeHourInteger = timeHourInteger*10; 
        timeHourInteger += timeHourMinute[0].charAt(1) == '0' ? 0 : Integer.parseInt(timeHourMinute[0].charAt(1)+"");
        
        calendar.set(Calendar.HOUR_OF_DAY,timeHourInteger);

        int timeMinuteInteger = 
        timeHourMinute[1].charAt(0) == '0' ? 0 : timeHourMinute[1].charAt(0)-'0';
        timeMinuteInteger = timeMinuteInteger*10;
        timeMinuteInteger += timeHourMinute[1].charAt(1) == '0' ? 0 : Integer.parseInt(timeHourMinute[1].charAt(1)+"");

        calendar.set(Calendar.MINUTE,timeMinuteInteger);
        calendar.set(Calendar.SECOND,0);

        return calendar.getTime();

    }

    public static boolean checkIfMettingInBufferTime(Date startTime,Date endTime){
        String startTimes = "09:01";
        String endTimes = "09:14";
        String startTimeHourMinute[] = startTimes.split(":");
        String endTimeHourMinute[] = endTimes.split(":");
        Date bufferStartTime = getExactDateWithTime(startTimeHourMinute);
        Date bufferEndTime = getExactDateWithTime(endTimeHourMinute);
        if(isOverlapping(startTime, endTime, bufferStartTime,
        bufferEndTime) || isOverlapping(bufferStartTime,bufferEndTime,startTime,endTime)){
                  return true;                                             
            }
        startTimes = "13:16";
        endTimes = "13:44";
        startTimeHourMinute = startTimes.split(":");
        endTimeHourMinute = endTimes.split(":");
        bufferStartTime = getExactDateWithTime(startTimeHourMinute);
        bufferEndTime = getExactDateWithTime(endTimeHourMinute);
        if(isOverlapping(startTime, endTime, bufferStartTime,
        bufferEndTime) || isOverlapping(bufferStartTime,bufferEndTime,startTime,endTime)){
                  return true;                                             
        }
        startTimes = "18:46";
        endTimes = "18:59";
        startTimeHourMinute = startTimes.split(":");
        endTimeHourMinute = endTimes.split(":");
        bufferStartTime = getExactDateWithTime(startTimeHourMinute);
        bufferEndTime = getExactDateWithTime(endTimeHourMinute);
        if(isOverlapping(startTime, endTime, bufferStartTime,bufferEndTime) 
          || isOverlapping(bufferStartTime,bufferEndTime,startTime,endTime)){
             return true;                                             
        }
        return false;
    }
}
