package API;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.client.HttpClient;

public class ApiIntro {


    @Test
    public void firstApiCall() throws IOException, URISyntaxException {

        //construct http client
        HttpClient httpClient = HttpClientBuilder.create().build();

        //construct a request
        //build an endpoit
        URIBuilder uri = new URIBuilder();
        uri.setScheme("https");
        uri.setHost("reqres.in");
        uri.setPath("api/users?page=2");

        //https://reqres.in/
        //construct a get method
        HttpGet httpGet = new HttpGet(uri.build());
        httpGet.setHeader("Accept","application/json");

        //execute a get request
        HttpResponse response = httpClient.execute(httpGet);

        System.out.println("Status code of my first api call is: "+ response.getStatusLine().getStatusCode());

        System.out.println(response.getEntity().getContentType().getValue());

        //status code verification
        Assert.assertEquals("Status code assertion failed", HttpStatus.SC_OK,response.getStatusLine().getStatusCode());

        //content type header verification
        //Assert.assertEquals();
    }

    @Test
    public void getListOfUers() throws IOException, URISyntaxException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https");
        uriBuilder.setHost("reqres.in");
        uriBuilder.setPath("api/users");
        uriBuilder.setCustomQuery("page=2");

        //or
        uriBuilder.setScheme("https").setHost("reqres.in").setPath("/api/users").setCustomQuery("page=2");

        //https://reqres.in/
        //construct a get method
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept","application/json");

        //execute a get request
        HttpResponse response = httpClient.execute(httpGet);
        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(response.getEntity().getContentType().getValue());

        Assert.assertEquals(HttpStatus.SC_OK,response.getStatusLine().getStatusCode());
        Assert.assertTrue(response.getEntity().getContentType().getValue().contains("application/json"));
        Assert.assertTrue(response.getEntity().getContentType().getValue().startsWith("application/json"));

    }

    @Test
    public void getUserById() throws URISyntaxException, IOException {
        //Construct http client
        HttpClient httpClient = HttpClientBuilder.create().build();
        //Construct a request
        //build an endpoint
        URIBuilder uri = new URIBuilder();
        uri.setScheme("https"); //specify schema
        uri.setHost("reqres.in"); //specify host
        uri.setPath("api/users/2"); //specify path

        //construct a get method
        HttpGet httpGet = new HttpGet(uri.build());
        httpGet.setHeader("Accept", "application/json");

        //execute a get request
        HttpResponse response = httpClient.execute(httpGet);
        Assert.assertEquals("Status code assertion failed",
                HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        Assert.assertTrue("Invalid content type header",
                response.getEntity().getContentType().getValue().contains("application/json"));
        System.out.println(response.getEntity().getContentType());

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> deserializedObject = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<Map<String, Object>>(){});
        System.out.println(deserializedObject.keySet());
        System.out.println(deserializedObject.size());
        System.out.println(deserializedObject.get("data"));

        Map<String, Object> dataValue = (Map<String, Object>)deserializedObject.get("data");
        System.out.println("----------------------");
        printData(dataValue);

        Map<String, String> adValues = (Map<String, String>) deserializedObject.get("ad");
        System.out.println("The company name is: "+adValues.get("company"));
        System.out.println("The company's url is: "+adValues.get("url"));
        System.out.println("Description: "+adValues.get("text"));


    }

    public void printData(Map<String, Object> dataValue){

        for(String key:dataValue.keySet()){
            System.out.printf("User's %s is %s\n", key, dataValue.get(key));//print formatted %s--> means string, \n --> new line, %d --> integer
        }
    }
}
