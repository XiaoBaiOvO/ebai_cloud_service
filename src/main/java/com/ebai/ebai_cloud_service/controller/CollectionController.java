package com.ebai.ebai_cloud_service.controller;

import com.ebai.ebai_cloud_service.common.util.impl.DateToolUtil;
import com.ebai.ebai_cloud_service.controller.request.CollectedStatusRequest;
import com.ebai.ebai_cloud_service.controller.request.CollectionRequest;
import com.ebai.ebai_cloud_service.controller.request.UploadRequest;
import com.ebai.ebai_cloud_service.controller.response.CollectedDataResponse;
import com.ebai.ebai_cloud_service.controller.response.CommonResponse;
import com.ebai.ebai_cloud_service.controller.response.RestResponse;
import com.ebai.ebai_cloud_service.mapper.CollectionDataRepository;
import com.ebai.ebai_cloud_service.mapper.CollectionNameRepository;
import com.ebai.ebai_cloud_service.mapper.entity.CollectionDataEntity;
import com.ebai.ebai_cloud_service.mapper.entity.CollectionNameEntity;
import com.ebai.ebai_cloud_service.model.vo.CollectionDataVo;
import com.ebai.ebai_cloud_service.model.vo.SubmittedDataVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@CrossOrigin
public class CollectionController extends BaseController{

    @Resource
    CollectionDataRepository collectionDataRepository;

    @Resource
    CollectionNameRepository collectionNameRepository;

    @PostMapping(value = "/api/upload")
    public CommonResponse upload (@RequestParam("file") MultipartFile multipartFile, UploadRequest request) throws IOException {

        log.info(request.getFileId());
        LocalTime now= LocalTime.now();
        String filePath = "D://collection//" + request.getFormType() + "//" + LocalDate.now() + "//" + request.getClassNumber() + "//" + request.getName();
        String fileSuffix = Objects.requireNonNull(multipartFile.getOriginalFilename()).substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        String fileName = filePath + "//" + request.getFileType() + "#" + LocalDate.now() + "#" + now.getHour()  + "-" + now.getMinute() + "-" + now.getSecond() + "#" + request.getFileId() + fileSuffix;
        File filePathFile = new File(filePath);
        File[] filePathList = filePathFile.listFiles();
        if (filePathList == null) {
            boolean result = filePathFile.mkdirs();
        }
        File file = new File(fileName);
        multipartFile.transferTo(file);
        log.info("成功接收文件: {}", fileName);
        return CommonResponse.builder().message("上传成功").build();
    }

    @PostMapping(value = "/api/submitCollectionForm")
    public CommonResponse submitCollectionForm (@RequestBody CollectionRequest request) {

        log.info(request.toString());

        CollectionDataEntity collectionDataEntity = collectionDataRepository.findTopByFormTypeAndClassNumberAndNameAndDate(
                request.getFormType(),
                request.getClassNumber(),
                request.getName(),
                LocalDate.now().toString());

        if (Objects.equals(collectionDataEntity, null)) {
            collectionDataEntity = new CollectionDataEntity();
        }

        collectionDataEntity.setFormType(request.getFormType());
        collectionDataEntity.setClassNumber(request.getClassNumber());
        collectionDataEntity.setName(request.getName());
        collectionDataEntity.setLocation(request.getLocation());
        collectionDataEntity.setPosition(request.getPosition());
        collectionDataEntity.setDate(LocalDate.now().toString());
        collectionDataEntity.setTime(LocalTime.now().toString());
        collectionDataEntity.setDatetime(LocalDateTime.now());
        //Todo Check file exist

        if (request.getFormType().equals("双码")) {
            collectionDataEntity.setHealthCodeFileId(request.getTwoCodeFileId().get(0));
            collectionDataEntity.setTravelCodeFileId(request.getTwoCodeFileId().get(0));
        } else if (request.getFormType().equals("核酸检测截图")) {
            collectionDataEntity.setTestScreenshotFileId(request.getTestScreenshotFileId());
        }

        collectionDataRepository.save(collectionDataEntity);

        return CommonResponse.builder().message("提交成功").data(request).build();
    }

    @RequestMapping(value = "/api/collection/getCollectedStatus")
    public RestResponse<CollectedDataResponse> getCollectedStatus (@RequestBody CollectedStatusRequest request) {
        log.info(request.toString());
        CollectionDataVo requestData = queryCollectionData(request.getFormType(), request.getClassNumber(), DateToolUtil.getDateString(request.getDate()));
        CollectionDataVo anotherData = queryCollectionData((Objects.equals(request.getFormType(), "双码") ? "核酸检测截图" : "双码"), request.getClassNumber(), DateToolUtil.getDateString(request.getDate()));
        List<CollectionDataVo> dataList = new ArrayList<>();
        dataList.add(Objects.equals(request.getFormType(), "双码") ? requestData : anotherData);
        dataList.add(Objects.equals(request.getFormType(), "双码") ? anotherData : requestData);
        CollectedDataResponse collectedDataResponse = new CollectedDataResponse();
        collectedDataResponse.setClassNumber(request.getClassNumber());
        collectedDataResponse.setCollectionData(requestData);
        collectedDataResponse.setCollectionDataList(dataList);

        List<CollectionDataEntity> collectedEntityList = collectionDataRepository.findAllByFormTypeAndClassNumberAndDate(
                request.getFormType(),
                request.getClassNumber(),
                DateToolUtil.getDateString(request.getDate()));
        List<CollectionNameEntity> totalNameEntityList = collectionNameRepository.findAllByRequestFormAndClassNumber(
                request.getFormType(),
                request.getClassNumber());
        List<String> totalNameList = new ArrayList<>();
        for(CollectionNameEntity entity : totalNameEntityList) {
            totalNameList.add(entity.getName());
        }
        List<SubmittedDataVo> submittedDataList = new ArrayList<>();
        for (String name : totalNameList) {
            SubmittedDataVo submittedData = new SubmittedDataVo();
            submittedData.setName(name);
            submittedData.setIsSubmitted(false);
            for (CollectionDataEntity collectionDataEntity : collectedEntityList) {
                if (Objects.equals(collectionDataEntity.getName(), name)) {
                    submittedData.setIsSubmitted(true);
                    submittedData.setTime(collectionDataEntity.getTime());
                }
            }
            submittedDataList.add(submittedData);
        }
        collectedDataResponse.setDataList(submittedDataList);
        return standardResponse(collectedDataResponse);
    }

    private CollectionDataVo queryCollectionData(String formType, String classNumber, String date) {
        List<CollectionDataEntity> collectedEntityList = collectionDataRepository.findAllByFormTypeAndClassNumberAndDate(
                formType,
                classNumber,
                date);
        List<CollectionNameEntity> totalNameEntityList = collectionNameRepository.findAllByRequestFormAndClassNumber(
                formType,
                classNumber);

        List<String> collectedList = new ArrayList<>();
        for(CollectionDataEntity entity : collectedEntityList) {
            collectedList.add(entity.getName());
        }

        List<String> totalNameList = new ArrayList<>();
        for(CollectionNameEntity entity : totalNameEntityList) {
            totalNameList.add(entity.getName());
        }

        List<String> submittedList = new ArrayList<>();
        for (String name : collectedList) {
            if (totalNameList.contains(name)) {
                submittedList.add(name);
            }
        }
        List<String> reminderList = new ArrayList<>();
        for (String name : totalNameList) {
            if (!collectedList.contains(name)) {
                reminderList.add(name);
            }
        }

        CollectionDataVo collectionDataVo = new CollectionDataVo();
        collectionDataVo.setClassNumber(classNumber);
        collectionDataVo.setFormType(formType);
        collectionDataVo.setCollectedCount(collectedList.size());
        collectionDataVo.setSubmittedCount(submittedList.size());
        collectionDataVo.setTotalCount(totalNameList.size());
        collectionDataVo.setReminderCount(reminderList.size());
        collectionDataVo.setNameList(submittedList);
        collectionDataVo.setReminderList(reminderList);
        return collectionDataVo;
    }

}
