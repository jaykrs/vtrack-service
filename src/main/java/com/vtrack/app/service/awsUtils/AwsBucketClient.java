package com.vtrack.app.service.awsUtils;

import java.io.File;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class AwsBucketClient extends AwsAuthentication{

	public static AWSCredentials credentials;
	public static AmazonS3 s3client;
	static {
		credentials = authenticateAWS();
		s3client = AmazonS3ClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_2).build();

	}
	
	public void storeFile() {
		s3client.putObject(
				  "bucketName", 
				  "Document/hello.txt", 
				  new File("/Users/user/Document/hello.txt")
				);
		}
	
}
