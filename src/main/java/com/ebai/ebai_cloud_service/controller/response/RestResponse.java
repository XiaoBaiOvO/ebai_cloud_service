package com.ebai.ebai_cloud_service.controller.response;

public final class RestResponse<T> {
    private static final int SUCCESS = 1;
    private static final int FAILURE = 99;
    private static final int TIME_OUT = 10;
    private int code;
    private String description;
    private T resultObject;

    public void setSuccess() {
        this.code = 1;
    }

    public void setFailure() {
        this.code = 99;
    }

    public void setTimeout() {
        this.code = 10;
    }

    public static <T> RestResponseBuilder<T> builder() {
        return new RestResponseBuilder();
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String toString() {
        return "RestResponse(code=" + this.getCode() + ", description=" + this.getDescription() + ", resultObject=" + this.getResultObject() + ")";
    }

    public RestResponse() {
    }

    public RestResponse(int code, String description, T resultObject) {
        this.code = code;
        this.description = description;
        this.resultObject = resultObject;
    }

    public int getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public T getResultObject() {
        return this.resultObject;
    }

    public void setResultObject(T resultObject) {
        this.resultObject = resultObject;
    }

    public static class RestResponseBuilder<T> {
        private int code;
        private String description;
        private T resultObject;

        RestResponseBuilder() {
        }

        public RestResponseBuilder<T> code(int code) {
            this.code = code;
            return this;
        }

        public RestResponseBuilder<T> description(String description) {
            this.description = description;
            return this;
        }

        public RestResponseBuilder<T> resultObject(T resultObject) {
            this.resultObject = resultObject;
            return this;
        }

        public RestResponse<T> build() {
            return new RestResponse(this.code, this.description, this.resultObject);
        }

        public String toString() {
            return "RestResponse.RestResponseBuilder(code=" + this.code + ", description=" + this.description + ", resultObject=" + this.resultObject + ")";
        }
    }
}