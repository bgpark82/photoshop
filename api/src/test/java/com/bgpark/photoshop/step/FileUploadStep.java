package com.bgpark.photoshop.step;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.specification.MultiPartSpecification;

public class FileUploadStep {

    public static ExtractableResponse<Response> 이미지_저장_요청(MultiPartSpecification multiPartSpecification) {
        return RestAssured
                .given().log().all()
                .multiPart(multiPartSpecification)
                .when().post("/api/v1/upload")
                .then().log().all().extract();
    }
}
