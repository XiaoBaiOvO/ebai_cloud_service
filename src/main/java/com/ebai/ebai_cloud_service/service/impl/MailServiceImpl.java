package com.ebai.ebai_cloud_service.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ebai.ebai_cloud_service.common.util.DateTool;
import com.ebai.ebai_cloud_service.common.util.Network;
import com.ebai.ebai_cloud_service.mapper.ClassScheduleRepository;
import com.ebai.ebai_cloud_service.mapper.entity.ClassScheduleEntity;
import com.ebai.ebai_cloud_service.model.dto.MailRequest;
import com.ebai.ebai_cloud_service.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.time.*;
import java.util.*;

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

        List<ClassScheduleEntity> classSchedule = classScheduleRepository.findAllByDateOrderByOrder(todayWeek);
        StringBuilder classScheduleMsg = new StringBuilder("<div style=\"padding-top: 10px\">今天没有课程安排，注意休息哦</div>\n");
        if (!classSchedule.isEmpty()) {
            classScheduleMsg = new StringBuilder("" +
                    "    <div style=\"padding: 10px 0\">今日课程表：</div>\n" +
                    "    <div>\n" +
                    "      <table style=\"font-size: 30px; text-align: center;border: 1px solid;border-collapse: collapse; table-layout: fixed; color: #9aa2d7\">\n" +
                    "        <tr>\n" +
                    "          <th style=\"border: 2px solid #9aa2d7; font-weight: bold\">时间</th>\n" +
                    "          <th style=\"border: 2px solid #9aa2d7; font-weight: bold\">课程</th>\n" +
                    "          <th style=\"border: 2px solid #9aa2d7; font-weight: bold\">教室</th>\n" +
                    "        </tr>\n");
            for (ClassScheduleEntity classes : classSchedule) {
                System.out.println(classes.getCauses());
                classScheduleMsg.append("" +
                        "        <tr>\n" +
                        "          <td style=\"border: 2px solid #9aa2d7\">" + classes.getTime() + "</td>\n" +
                        "          <td style=\"border: 2px solid #9aa2d7\">" + classes.getCauses() + "</td>\n" +
                        "          <td style=\"border: 2px solid #9aa2d7\">" + classes.getClassroom() + "</td>\n" +
                        "        </tr>\n");
            }
            classScheduleMsg.append("" +
                    "      </table>\n" +
                    "    </div>\n");
        }

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
                "    <div style=\"padding-top: 10px\">最高气温：" + todayHighTemp + "℃ 最低气温：" + todayLowTemp + "℃</div>\n" + weatherChangeMsg + classScheduleMsg +
                "    <div style=\"padding: 10px 0\">距HYC前来探监还有：好多好多天</div>\n" +
                "    <img src=\"https://img.72qq.com/file/202103/02/7fcba33957.jpg\" alt=\"\" style=\"width: 200px\">\n" +
                "    <div><table style=\"table-layout: fixed;\"></table></div>\n" +
                "  </div>\n" +
                "</div>" +
                "";

        List<MailRequest> mailList = new ArrayList<>();
        MailRequest mail1 = new MailRequest();
        mail1.setTitle(title);
        mail1.setMessage(message);
        mail1.setSender("HYC大聪明");
        mail1.setRecipient("HMQ小宝贝");
        mail1.setRecipientAccount("2643372457@qq.com");
        mailList.add(mail1);
        MailRequest mail2 = new MailRequest();
        mail2.setTitle(title);
        mail2.setMessage(message);
        mail2.setSender("HYC大聪明");
        mail2.setRecipient("HMQ小宝贝");
        mail2.setRecipientAccount("2081414628@qq.com");
        mailList.add(mail2);

        return network.sendMail(mailList) ? "发送成功" : "发送失败";
    }

    @Override
    public void startAutoDailyMail(Integer timeSet) {
        log.info("启动定时邮件任务 =>");
        LocalDate date = LocalDate.now();
        LocalTime time = LocalTime.now();
        if (time.getHour() >= timeSet) {
            date = LocalDate.now().plusDays(BigInteger.ONE.longValue());
        }
        LocalDateTime firstDateTime = LocalDateTime.of(date, LocalTime.of(timeSet, 0));
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
