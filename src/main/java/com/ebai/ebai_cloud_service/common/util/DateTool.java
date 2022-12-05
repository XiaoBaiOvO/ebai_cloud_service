package com.ebai.ebai_cloud_service.common.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public interface DateTool {

    Date todayDate();

    LocalDate todayLocalDate();

    LocalDateTime todayLocalDateTime();

    Date setDate(int year, int month, int date, int hourOfDay, int minute, int second);
}
