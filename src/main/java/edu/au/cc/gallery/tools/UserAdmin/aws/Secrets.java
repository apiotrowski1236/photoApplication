package edu.au.cc.gallery.tools.aws;


import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.*;
import software.amazon.awssdk.services.secretsmanager.model.DecryptionFailureException;
import software.amazon.awssdk.services.secretsmanager.model.InternalServiceErrorException;
import software.amazon.awssdk.services.secretsmanager.model.InvalidParameterException;
import software.amazon.awssdk.services.secretsmanager.model.InvalidRequestException;
import software.amazon.awssdk.services.secretsmanager.model.ResourceNotFoundException;
import software.amazon.awssdk.regions.Region;

 

public class  Secrets {

public static String getImageGallerySecret() {
    //Initialize the name & region variables.
    String secretName = "sec-ig-image-gallery";
    String region = "us-east-2";

    // Create a Secrets Manager client
    AWSSecretsManager client  = AWSSecretsManagerClientBuilder.standard().withRegion(region).build();
    GetSecretValueRequest request = new GetSecretValueRequest().withSecretId(secretName);
    GetSecretValueResult  response  = null;

    try {
	response = client.getSecretValue(request);
    } catch (DecryptionFailureException e) {
        throw e;
    } catch (InternalServiceErrorException e) {
        throw e;
    } catch (InvalidParameterException e) {
        throw e;
    } catch (InvalidRequestException e) {
        throw e;
    } catch (ResourceNotFoundException e) {
        throw e;
    }
    return response.getSecretString();

}
}
