package AmazonS3.amazondemo;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

public class App 
{
	
	static String accessKey = "**********************************";
	static String secretAccessKey = "********************************************";
	
	
    public static void main( String[] args )
    {
        System.out.println( "Demo for Amazon S3 connectivity from Standalone Java code!" );
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretAccessKey);
        
        AmazonS3 amazonS3Client = new AmazonS3Client(awsCredentials);
        
        System.out.println("Bucket is being created ..................");
        String bucketName = "bucktbyjavacode";
        amazonS3Client.createBucket(bucketName);
        
        for(Bucket bucket : amazonS3Client.listBuckets()){
        	System.out.println("Bucket in Amazon S3 :"+bucket.getName());
        }
        
        System.out.println("Folder is being created ................");
        String newFolderName = "Folder_by_Java_Code";
        App demo = new App();
        demo.createNewFolder(bucketName, newFolderName, amazonS3Client);
        String fileName = "demotextfile.txt";
        amazonS3Client.putObject(new PutObjectRequest(bucketName, fileName, new File("D:\\demotextfile.txt")));
        
        System.out.println("All Done ............");
        
    }
    
    private void createNewFolder(String bucketName, String newFolderName, AmazonS3 amazonClient){
    	ObjectMetadata metaData = new ObjectMetadata();
    	metaData.setContentLength(0);
    	
    	InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
    	
    	PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, newFolderName, emptyContent, metaData);
    	
    	amazonClient.putObject(putObjectRequest);
    }
}


 


