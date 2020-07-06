package edu.au.cc.gallery.tools.UserAdmin.authentication;
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

    String secretName = System.getenv("IG_SECRET");

    //Look in the Secrets Manager if the IG_Secret is not present. 
    if (secretName == null) {
	secretName = "m6_secret";

	String region = "us-east-2";

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
    //Simply return the environment variable if it is present. 
    else {
	return secretName;
    }
  }
}
