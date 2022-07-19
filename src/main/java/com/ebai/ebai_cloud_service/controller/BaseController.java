package com.ebai.ebai_cloud_service.controller;

import com.ebai.ebai_cloud_service.annotation.MyAnnotation;
import com.ebai.ebai_cloud_service.service.BaseService;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;

@Slf4j
@RestController
@CrossOrigin
public class BaseController {

    @Resource
    BaseService baseService;


    @GetMapping(value = "/test")
    public String test() {
        log.info("test success");
        return "Success !";
    }

    @Setter
    @GetMapping(value = "/download")
    public void download(HttpServletRequest request, HttpServletResponse response) {
        String fileName = "SW_DVD9_Win_Pro_10_20H2.4_64ARM_ChnSimp_Pro_Ent_EDU_N_MLF_X22-52787.ISO";
        response.reset();
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment; filename=" + new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
        String baseFilePath = "D:\\Download\\" + fileName;
        System.out.println(baseFilePath);
        File file = new File(baseFilePath);
        try (InputStream in = Files.newInputStream(file.toPath());
             OutputStream os = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            os.flush();
        } catch (Exception e) {
            System.out.println("失败");
        }

    }

    @ResponseBody
    @GetMapping(value = "/insert")
    public String insert() {
        log.info("insert");

        baseService.insert();

        return "Success !";
    }

    @GetMapping(value = "/user")
    @ResponseBody
    @MyAnnotation(value = "ABC")
    public String user() {
        return baseService.user();
    }

    @ResponseBody
    @PostMapping(value = "/query")
    public Boolean query(@RequestBody SubmitForm submitForm) {
        log.info("query");
        log.info(submitForm.userName);
        log.info(submitForm.password);
        log.info(submitForm.verifyCode);

        if (Objects.equals(submitForm.userName, "hmq") && Objects.equals(submitForm.password, "072822")) {
            return true;
        }
        if (Objects.equals(submitForm.userName, "191530050284") && Objects.equals(submitForm.password, "072822")) {
            return true;
        }
        return false;
    }

    @Getter
    private static class SubmitForm {

        String userName;

        String password;

        String verifyCode;

    }






}
