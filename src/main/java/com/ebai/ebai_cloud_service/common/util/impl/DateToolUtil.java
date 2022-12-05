package com.ebai.ebai_cloud_service.common.util.impl;

import com.ebai.ebai_cloud_service.common.util.DateTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

@Service
@Slf4j
public class DateToolUtil implements DateTool {

    @Override
    public Date todayDate() {
        Date nowDate = new Date();
        nowDate.setHours(0);
        nowDate.setMinutes(0);
        nowDate.setSeconds(0);
        return nowDate;
    }

    @Override
    public LocalDate todayLocalDate() {
        Date todayDate = todayDate();
        return todayDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    @Override
    public LocalDateTime todayLocalDateTime() {
        Date todayDate = todayDate();
        return todayDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    @Override
    public Date setDate(int year, int month, int date, int hourOfDay, int minute, int second) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month,date, hourOfDay,minute, second);
        return cal.getTime();
    }

    public static String getDateString(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString();
    }


}
