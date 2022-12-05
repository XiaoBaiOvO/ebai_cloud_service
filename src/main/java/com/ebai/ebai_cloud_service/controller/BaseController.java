package com.ebai.ebai_cloud_service.controller;

import com.ebai.ebai_cloud_service.controller.response.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin
public class BaseController {

    public static final int PAGE_SIZE = 15;

    /**
     * The Constant ROWS.
     */
    public static final String ROWS = "15";

    /**
     * Standard response.
     *
     * @param <T> the generic type
     * @return the rest response
     */
    protected final <T> RestResponse<T> standardResponse() {
        return this.standardResponse(null, null);
    }

    /**
     * Standard response.
     *
     * @param <T> the generic type
     * @param object the object
     * @return the rest response
     */
    protected final <T> RestResponse<T> standardResponse(T object) {
        return this.standardResponse(object, null);
    }

    /**
     * Standard response.
     *
     * @param <T> the generic type
     * @param description the description
     * @return the rest response
     */
    protected final <T> RestResponse<T> standardResponse(String description) {
        return this.standardResponse(null, description);
    }

    /**
     * Standard response.
     *
     * @param <T> the generic type
     * @param object the object
     * @param description the description
     * @return the rest response
     */
    protected final <T> RestResponse<T> standardResponse(T object, String description) {
        final RestResponse<T> restResponse = new RestResponse<>();
        restResponse.setSuccess();

        if (object != null) {
            restResponse.setResultObject(object);
        }

        if (StringUtils.isNotBlank(description)) {
            restResponse.setDescription(description);
        }

        return restResponse;
    }

    /**
     * Fail response.
     *
     * @param <T>
     *            the generic type
     * @return the rest response
     */
    protected final <T> RestResponse<T> failResponse() {
        return this.failResponse(null, null);
    }

    /**
     * Fail response.
     *
     * @param <T>
     *            the generic type
     * @param object
     *            the object
     * @return the rest response
     */
    protected final <T> RestResponse<T> failResponse(T object) {
        return this.failResponse(object, null);
    }

    /**
     * Fail response.
     *
     * @param <T>
     *            the generic type
     * @param description
     *            the description
     * @return the rest response
     */
    protected final <T> RestResponse<T> failResponse(String description) {
        return this.failResponse(null, description);
    }

    /**
     * Fail response.
     *
     * @param <T>
     *            the generic type
     * @param object
     *            the object
     * @param description
     *            the description
     * @return the rest response
     */
    protected final <T> RestResponse<T> failResponse(T object, String description) {
        final RestResponse<T> restResponse = new RestResponse<>();
        restResponse.setFailure();

        if (object != null) {
            restResponse.setResultObject(object);
        }

        if (StringUtils.isNotBlank(description)) {
            restResponse.setDescription(description);
        }

        return restResponse;
    }

}
