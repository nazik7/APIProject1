package StepDefinitions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ListOfUsersStepDefs {

    HttpClient httpClient = HttpClientBuilder.create().build();
    URIBuilder uriBuilder = new URIBuilder();
    HttpGet httpGet;
    HttpResponse response;
    ObjectMapper objectMapper = new ObjectMapper();
    Map<String, Object> deserializedObject;

    Properties properties;
    FileInputStream fileInputStream;


    public ListOfUsersStepDefs() throws URISyntaxException, IOException {
       properties =  new Properties();
       fileInputStream = new FileInputStream(new File("configuration.properties"));
       properties.load(fileInputStream);
    }


    @Given("constucted Http client with parameters")
    public void constucted_Http_client_with_parameters(Map<String, String> parameters) throws URISyntaxException {
        uriBuilder.setScheme("https").setHost(properties.getProperty("host")).setPath(parameters.get("endpoint")).setCustomQuery(parameters.get("query"));
        httpGet = new HttpGet(uriBuilder.build());
    }

    @And("I set custom headers")
    public void i_set_custom_headers() {
        httpGet.setHeader("Accept", properties.getProperty("accept"));
        httpGet.setHeader("Content-Type",properties.getProperty("accept"));
    }

    @When("the user sends Get request")
    public void the_user_sends_Get_request() throws IOException {
        response = httpClient.execute(httpGet);
    }

    @Then("validates the status code is {int}")
    public void validates_the_status_code_is(int statusCode) {
        Assert.assertEquals(statusCode,response.getStatusLine().getStatusCode());
        System.out.println("The status code is: "+response.getStatusLine().getStatusCode());
    }

    @And("I validate the header is {string}")
    public void i_validate_the_header_is(String header) {
        Assert.assertTrue(response.getEntity().getContentType().getValue().contains(header));
        System.out.println(response.getEntity().getContentType().getValue());
    }

    @And("I validate response contains attributes:")
    public void i_validate_response_contains_attributes(List<String> attributes) throws IOException {

        deserializedObject = objectMapper.readValue(response.getEntity().getContent(),
                new TypeReference<Map<String, Object>>(){});
        System.out.println("Response is: " + deserializedObject.toString());
        String responsePayLoad = deserializedObject.toString();

        for(String attribute:attributes){
            Assert.assertTrue(""+attribute + " does not exist in the response",responsePayLoad.contains(attribute));
        }

        System.out.println("ResponsePayLoad contains all attributes");

    }


    @And("I validate response attributes and values:")
    public void I_Validate_Response_Attributes_And_Values(Map<String, String> attributeValues) throws IOException {

        Map<String, Object> actualAttributeValues = (Map<String, Object>) deserializedObject;

        for(String key:attributeValues.keySet()){
            String expectedValue = attributeValues.get(key);
            String actualValue = actualAttributeValues.get(key).toString();
            Assert.assertEquals("Value of "+key + " is not the same, expected:" +expectedValue+
                   "but found "+actualValue , expectedValue, actualValue);
            System.out.println("Value at " +key + " is " +actualValue);
        }
        System.err.println("Response attributes are as expected");

    }

    @And("I validate the data of the first user")
    public void I_Validate_The_Data_Of_The_First_User(Map<String, String> expectedFirstUserValues) {

        List<Object> usersValues = (List <Object>)deserializedObject.get("data");
        Map<String, Object> actualFirstUserValues = (Map<String, Object>) usersValues.get(0);
        for(String key:expectedFirstUserValues.keySet()){
            Assert.assertEquals(expectedFirstUserValues.get(key), actualFirstUserValues.get(key).toString());
        }
        System.out.println("All first user values are as expected.");

    }
}
