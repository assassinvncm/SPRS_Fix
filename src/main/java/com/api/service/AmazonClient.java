package com.api.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.util.Base64;
import com.api.dto.ImageDto;
import com.ultils.Constants;

@Service
public class AmazonClient {
	private AmazonS3 s3client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;
    
    @PostConstruct
    private void initializeAmazon() {
       AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
       this.s3client = new AmazonS3Client(credentials);
    }
    
    private File convertBase64ToFile(ImageDto image) throws IOException {
    	byte[] imageByte= Base64.decode(image.getEncodedImage());
        File convFile = new File(image.getImageName());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(imageByte);
        fos.close();
        return convFile;
    }
    
    private String generateFileName(ImageDto image) {
        return new Date().getTime() + "-" + image.getImageName().replace(" ", "_");
    }
    
    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(bucketName, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }
    
    public String uploadFile(ImageDto image) {

        String fileUrl = "";
        try {
            File file = convertBase64ToFile(image);
            String fileName = generateFileName(image);
//            fileUrl = "https://" + bucketName + "." + endpointUrl.substring(8) + "/" + fileName;
//            fileUrl = Constants.IMAGE_URL + fileName;
            fileUrl = fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return fileUrl;
    }
}
