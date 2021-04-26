package com.bgpark.photoshop.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.AnonymousAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import io.findify.s3mock.S3Mock;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import static com.bgpark.photoshop.domain.file.step.S3Step.*;

@TestConfiguration
@Profile("test")
public class S3MockConfig {

    @Bean
    public S3Mock s3Mock() {
        return new S3Mock.Builder()
                .withPort(MOCK_S3_SERVER_PORT)
                .withInMemoryBackend()
                .build();
    }

    @Bean
    @Primary
    public AmazonS3 amazonS3(S3Mock s3Mock) {
        s3Mock.start();

        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(new AnonymousAWSCredentials()))
                .withPathStyleAccessEnabled(true)
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(MOCK_S3_SERVER_URI, MOCK_S3_SERVER_REGION))
                .build();
    }


}
