package com.inditex.test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Utils {

    public static LocalDateTime getTimeByDayAndMonth(Integer day, Integer month){
        return LocalDateTime.of(LocalDate.of(2022, month,day), LocalTime.of(0,0,0));
    }
}
