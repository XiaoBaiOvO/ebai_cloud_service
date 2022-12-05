package com.ebai.ebai_cloud_service.controller.response;

import com.ebai.ebai_cloud_service.model.vo.CollectionDataVo;
import com.ebai.ebai_cloud_service.model.vo.SubmittedDataVo;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CollectedDataResponse {

    private String classNumber;

    private List<CollectionDataVo> collectionDataList;

    private CollectionDataVo collectionData;

    private List<SubmittedDataVo> dataList;

}
