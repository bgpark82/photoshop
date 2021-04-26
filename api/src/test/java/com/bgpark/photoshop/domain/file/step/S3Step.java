package com.bgpark.photoshop.domain.file.step;

import com.amazonaws.regions.Regions;

public class S3Step {

    public static final String MOCK_S3_BUCKET_NAME = "bg-nearlog";
    public static final String MOCK_S3_KEY_NAME = "media";
    public static final int MOCK_S3_SERVER_PORT = 8001;
    public static final String MOCK_S3_SERVER_URI = "http://localhost:" + MOCK_S3_SERVER_PORT;
    public static final String MOCK_S3_SERVER_REGION = Regions.AP_NORTHEAST_2.getName();
}
