package API;

import Utils.PayloadUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import java.util.Map;

public class PostIntro {

    @Test
    public void postPet() throws URISyntaxException, IOException {
        //construct a client
        HttpClient httpClient = HttpClientBuilder.create().build();

        URIBuilder uriBuilder = new URIBuilder();

        // https://petstore.swagger.io/v2/pet
        uriBuilder.setScheme("https");
        uriBuilder.setHost("petstore.swagger.io");
        uriBuilder.setPath("v2/pet");

        HttpPost httpPost = new HttpPost(uriBuilder.build());
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-Type", "application/json");

        int petId = 111;
        String requestBody = PayloadUtils.getPetPayload(petId);
        HttpEntity entity = new StringEntity(requestBody);
        httpPost.setEntity(entity);

        HttpResponse httpResponse = httpClient.execute(httpPost);
        Assert.assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
        Assert.assertEquals("application/json",httpResponse.getEntity().getContentType().getValue());

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> petDetails = objectMapper.readValue(httpResponse.getEntity().getContent(),
                new TypeReference<Map<String, Object>>(){});
        Assert.assertEquals(petId, petDetails.get("id"));


    }


}
