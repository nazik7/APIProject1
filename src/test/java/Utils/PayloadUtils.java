package Utils;

public class PayloadUtils {

    public static String getPetPayload(Integer id){

        String requestBody = "{\n" +
                "    \"id\": "+id+"\n" +
                "    \"category\": {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"shepard\"\n" +
                "    },\n" +
                "    \"name\": \"Tony\",\n" +
                "    \"photoUrls\": [\n" +
                "        \"https://s3.amazon.com/myPet.jpg\"\n" +
                "    ],\n" +
                "    \"tags\": [\n" +
                "        {\n" +
                "            \"id\": 2,\n" +
                "            \"name\": \"string\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"status\": \"not available\"\n" +
                "}";

        return requestBody;
    }
}
