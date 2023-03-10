package com.company.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;

public class AwsConfigure {

    private static final String REGION="us-east-1";
    private static final String ACCESS_KEY="AKIA4PVDS5EDSTGPYBKI";
    private static final String SECRET_KEY="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";


@Bean
    public AmazonS3 s3Client(){
    return AmazonS3ClientBuilder
            .standard()
            .withRegion(REGION).withCredentials
          (new AWSStaticCredentialsProvider(new BasicAWSCredentials(ACCESS_KEY,SECRET_KEY)))
                    .build();
}

}
