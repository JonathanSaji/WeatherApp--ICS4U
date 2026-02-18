package jhn;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Time {
    boolean matchTime = false;
    public Time(String title,LocalDateTime date){
        int currentHour = date.getHour();
        String amOrPm = currentHour >= 12 ? " PM" : " AM";
        String compare;

        if(currentHour == 0){
            currentHour = 12;
        }
        else if(currentHour > 12){
            currentHour %= 12;
        }
        
        compare = currentHour + amOrPm;
        System.out.println(compare);
        if(compare.equals(title)){
            matchTime = true;
        }
        else{
            matchTime = false;
        }

    }

    public boolean getMatchTime(){
        return matchTime;
    }


    public static void main(String[] args) {
        LocalDateTime date = LocalDateTime.now();
        System.out.println(new Time("4 PM",date).getMatchTime());;

    }
}   
