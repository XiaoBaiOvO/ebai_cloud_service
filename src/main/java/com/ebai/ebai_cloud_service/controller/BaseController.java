package com.ebai.ebai_cloud_service.controller;

import com.ebai.ebai_cloud_service.model.service.MailService;
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

    @GetMapping(value = "/download")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "19042.1469.220115-2112.20H2_RELEASE_SVC_IM_CLIENTPRO_OEMRET_A64FRE_ZH-CN.ISO";
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

}
