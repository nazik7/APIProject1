package API;

import Utils.Driver;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

public class GetListOfUsers {

    WebDriver driver = Driver.getDriver();

    @Test
    public void getUsers() throws URISyntaxException, IOException {
        //Construct http client
        HttpClient httpClient = HttpClientBuilder.create().build();
        //Construct a request
        //build an endpoint

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setScheme("https").setHost("reqres.in").setPath("api/users").setCustomQuery("per_page=12");

        //construct a get method
        HttpGet httpGet = new HttpGet(uriBuilder.build());
        httpGet.setHeader("Accept", "application/json");

        //execute a get request
        HttpResponse response = httpClient.execute(httpGet);

        Assert.assertEquals("Status code assertion failed",
                HttpStatus.SC_OK, response.getStatusLine().getStatusCode());
        Assert.assertTrue("Invalid content type header",
                response.getEntity().getContentType().getValue().contains("application/json"));
        System.out.println(response.getEntity().getContentType());

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> deserializedObject = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>(){});
        System.out.println(deserializedObject.keySet());
        System.out.println(deserializedObject.size());

        List<Object> dataValues = (List<Object>)deserializedObject.get("data");
        System.out.println(dataValues.size());
        Assert.assertEquals(deserializedObject.get("per_page"),dataValues.size());

        System.out.println("----------------------");
        //print out all the users as list objects
        for(Object object:dataValues){
            System.out.println(object);
        }

        System.out.println("------------------");
        Map<String, Object> userInfo1 = ( Map<String, Object>) dataValues.get(0);
        //print out all information about the first user
        for(String key:userInfo1.keySet()){
            System.out.printf("User's %s is: %s\n", key, userInfo1.get(key));
        }
        System.out.println(userInfo1.get("id"));


        Map<String, String> adValues = (Map<String, String>) deserializedObject.get("ad");

        //print out all the names
        for(int i=0; i<dataValues.size();i++){
            Map<String, Object> userData = (Map<String, Object>) dataValues.get(i);
            System.out.println(userData.get("first_name"));
        }

        //get avatar links and make sure that the picture is displayed
        for(int i=0; i<dataValues.size();i++) {
            Map<String, Object> userData = (Map<String, Object>) dataValues.get(i);
            //System.out.println(userData.get("avatar"));
            driver.get(userData.get("avatar").toString());
            WebElement image = driver.findElement(By.xpath("//img"));
            Assert.assertTrue(image.isDisplayed());
            Assert.assertFalse(image.equals(null));
        }

    }

}
