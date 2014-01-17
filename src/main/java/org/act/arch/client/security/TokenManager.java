package org.act.arch.client.security;

import org.apache.http.HttpResponse;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;

public class TokenManager {
	
    private HTTPClient httpClient;    

    public TokenManager() {
        httpClient = new HTTPClient();
    }

    public Token getToken(String username, String password){
        String submitUrl = "http://localhost:8280/token";
        String consumerKey = "cvFci1ZZxza23MY5_ER6Px5eEpEa";
        String consumerSecret = "6MPCq2gB0P6vho8uOJB3smpAck0a";
        try {
            String applicationToken = consumerKey + ":" + consumerSecret;
           
            applicationToken = "Basic " + new Base64().encodeToString(applicationToken.getBytes()).trim();

            String payload = "grant_type=password&username="+username+"&password="+password;
            HttpResponse httpResponse = httpClient.doPost(submitUrl,applicationToken,
            		payload,"application/x-www-form-urlencoded");
            if (httpResponse.getStatusLine().getStatusCode() != 200) {
            	return null;
            }
            String response = httpClient.getResponsePayload(httpResponse);
            return JSONClient.getAccessToken(response);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}