package com.ebai.ebai_cloud_service.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ebai.ebai_cloud_service.common.util.DateTool;
import com.ebai.ebai_cloud_service.common.util.Network;
import com.ebai.ebai_cloud_service.mapper.ClassScheduleRepository;
import com.ebai.ebai_cloud_service.model.dto.MailRequest;
import com.ebai.ebai_cloud_service.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.time.*;
import java.util.Date;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import static java.util.concurrent.TimeUnit.DAYS;

@Service
@Slf4j
public class MailServiceImpl implements MailService {

    @Resource
    Network network;

    @Resource
    DateTool dateTool;

    @Resource
    ClassScheduleRepository classScheduleRepository;

    private static final String weatherRequest = "https://api.map.baidu.com/weather/v1/?district_id=310120&data_type=all&ak=Tco6gfz2hZFqtoXGIzoQavlz49dLCtOS";

    private static final LocalDateTime loveDay = LocalDateTime.of(2021, 8, 16, 0, 0, 0);

    @Override
    public String sendDailyMail() {

        JSONObject weather = network.getHttp(weatherRequest);

        JSONObject weatherResult = JSONObject.parseObject(weather.getString("result"));
        JSONObject nowResult = JSONObject.parseObject(weatherResult.getString("now"));
        String nowText = nowResult.get("text").toString();
        String nowTemp = nowResult.get("temp").toString();
        JSONArray forecastsResult = JSONObject.parseArray(weatherResult.getString("forecasts"));
        JSONObject todayForecastsResult = (JSONObject) forecastsResult.get(0);
        String todayWeek = todayForecastsResult.get("week").toString();
        String todayHighTemp = todayForecastsResult.get("high").toString();
        String todayLowTemp = todayForecastsResult.get("low").toString();
        String todayTextDay = todayForecastsResult.get("text_day").toString();
        String todayTextNight = todayForecastsResult.get("text_night").toString();
        LocalDate today = dateTool.todayLocalDate();

        String weatherChangeNotes = (Objects.equals(nowText, todayTextDay) && Objects.equals(todayTextDay, todayTextNight)) ? "" :
                ((Objects.equals(nowText, todayTextDay) && !Objects.equals(todayTextDay, todayTextNight)) ? "下午可能" + todayTextDay + "转" + todayTextNight :
                        ((!Objects.equals(nowText, todayTextDay) && Objects.equals(todayTextDay, todayTextNight)) ? "上午可能" + nowText + "转" + todayTextDay :
                                "上午可能" + nowText + "转" + todayTextDay + "，下午可能" + todayTextDay + "转" + todayTextNight));
        String weatherChangeMsg = ((weatherChangeNotes.equals("") ? "" : "<div style=\"padding-top: 10px\">" + weatherChangeNotes + "</div>\n"));

        String title = "小白の每日问候 - 爱你的第" + Duration.between(loveDay, LocalDateTime.now()).toDays() + "天";
        String message = "" +
                "<div style=\"width: 100%; background-color: antiquewhite;font-size: 30px;font-family: '楷体',serif;padding: 20px 0\">\n" +
                "  <div style=\"font-weight: bold;text-align: center;color: #d79ad0;font-size: 30px;\">\n" +
                "    <Strong>心梗砖家 tickled you ❤ ~</Strong>\n" +
                "    <hr>\n" +
                "  </div>\n" +
                "  <div style=\"color: #9aa2d7;margin-left: 20px\">\n" +
                "    <div style=\"padding-top: 10px\">早上好！今天是" + today.getMonthValue() + "月" + today.getDayOfMonth() + "日 " + todayWeek + "</div>\n" +
                "    <div style=\"padding-top: 10px\">今早上海天气：" + nowText + " " + nowTemp + "℃</div>\n" +
                "    <div style=\"padding-top: 10px\">最高气温：" + todayHighTemp + "℃ 最低气温：" + todayLowTemp + "℃</div>\n" + weatherChangeMsg +
                "    <div style=\"padding: 10px 0\">今日课程表：</div>\n" +
                "    <div>\n" +
                "      <table style=\"font-size: 30px; text-align: center;border: 1px solid;border-collapse: collapse; table-layout: fixed; color: #9aa2d7\">\n" +
                "        <tr>\n" +
                "          <th style=\"border: 2px solid #9aa2d7; font-weight: bold\">时间</th>\n" +
                "          <th style=\"border: 2px solid #9aa2d7; font-weight: bold\">课程</th>\n" +
                "          <th style=\"border: 2px solid #9aa2d7; font-weight: bold\">教室</th>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "          <td style=\"border: 2px solid #9aa2d7\">9:45-11:30</td>\n" +
                "          <td style=\"border: 2px solid #9aa2d7\">高等数学</td>\n" +
                "          <td style=\"border: 2px solid #9aa2d7\">4号楼A215</td>\n" +
                "        </tr>\n" +
                "        <tr>\n" +
                "          <td style=\"border: 2px solid #9aa2d7\">18:00-19:40</td>\n" +
                "          <td style=\"border: 2px solid #9aa2d7\">大学英语</td>\n" +
                "          <td style=\"border: 2px solid #9aa2d7\">4号楼A215</td>\n" +
                "        </tr>\n" +
                "      </table>\n" +
                "    </div>\n" +
                "    <div style=\"padding: 10px 0\">距HYC前来探监还有：好多好多天</div>\n" +
                "    <img src=\"https://img.72qq.com/file/202103/02/7fcba33957.jpg\" alt=\"\" style=\"width: 200px\">\n" +
                "  </div>\n" +
                "</div>" +
                "";

        MailRequest mail = new MailRequest();
        mail.setTitle(title);
        mail.setMessage(message);
        mail.setSender("HYC大聪明");
        mail.setRecipient("HMQ小宝贝");
        mail.setRecipientAccount("2643372457@qq.com");
        Boolean result1 = network.sendMail(mail);
        mail.setRecipientAccount("2081414628@qq.com");
        Boolean result2 = network.sendMail(mail);

        return (result1 && result2) ? "发送成功" : ((result1 ? null : "Task1 ") + (result1 ? null : "Task2 ") + "发送失败");
    }

    @Override
    public void startAutoDailyMail() {
        log.info("启动定时邮件任务 =>");
        LocalDate date;
        LocalTime time = LocalTime.now();
        if (time.getHour() < 6) {
            date = LocalDate.now();
        } else {
            date = LocalDate.now().plusDays(BigInteger.ONE.longValue());
        }
        LocalDateTime firstDateTime = LocalDateTime.of(date, LocalTime.of(6, 0));
        Date firstDate = Date.from(firstDateTime.atZone(ZoneId.systemDefault()).toInstant());
        log.info("=> {} 第一次发送", firstDate);
        TimerTask task = new TimerTask() {
            public void run() {
                log.info("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
                log.info("定时任务触发：Service == > SendDailyMail");
                log.info("------------------ Service  Log ------------------");
                sendDailyMail();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, firstDate, DAYS.toMillis(BigInteger.ONE.longValue()));
    }

}
