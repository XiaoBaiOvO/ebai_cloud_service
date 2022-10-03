package com.ebai.ebai_cloud_service.controller;

import com.ebai.ebai_cloud_service.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Slf4j
@RestController
@CrossOrigin
public class BaseController {
    @Resource
    MailService mailService;

    @GetMapping(value = "/jiangyi")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "【汤家凤】高等数学辅导讲义.pdf";
        response.reset();
        response.setContentType("binary/octet-stream");
        response.addHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
        String baseFilePath = "D:\\Download\\" + fileName;
        System.out.println(baseFilePath);
        File file = new File(baseFilePath);
        try (
                InputStream in = Files.newInputStream(file.toPath());
                OutputStream os = response.getOutputStream();
        ) {
            Integer fileSize = in.available();
            response.addHeader("content-length", String.valueOf(fileSize));
            response.addHeader("accept-ranges", "bytes");
            byte[] buffer = new byte[1024];
            int length;
            int finished = 0;
            int round = 0;
            while ((length = in.read(buffer)) > 0) {
                os.write(in.available());
                os.write(buffer, 0, length);
                finished ++;
                if (finished >= round * fileSize / 1024 / 10) {
                    log.info(round * 10+"%");round ++;
                }
            }
            os.flush();
            log.info("成功");
        } catch (Exception e) {
            log.warn("失败");
        }
    }

    @GetMapping(value = "/zhenti")
    public void download2(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "【新文道】考研数学5年秘赠真题（2017-2021）.pdf";
        response.reset();
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
        String baseFilePath = "D:\\Download\\" + fileName;
        System.out.println(baseFilePath);
        File file = new File(baseFilePath);
        try (
                InputStream in = Files.newInputStream(file.toPath());
                OutputStream os = response.getOutputStream();
        ) {
            log.info(String.valueOf(in.available()));
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                log.info(String.valueOf(length));
                os.write(buffer, 0, length);
            }
            os.flush();
            log.info("成功");
        } catch (Exception e) {
            log.warn("失败");
        }
    }

    @GetMapping(value = "/email")
    public String email() {
        return mailService.sendDailyMail();
    }

    @GetMapping(value = "/startAutoDailyMail")
    public void startAutoDailyMail() {
        mailService.startAutoDailyMail(6);
    }
}
