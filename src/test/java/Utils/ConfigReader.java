package Utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties configFile;

    static{
        String path = "configuration.properties";
        try {
            FileInputStream input = new FileInputStream(path);//opens the file
            configFile = new Properties();
            configFile.load(input);//loads all the information from the file
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String keyValue){
        return (String) configFile.get(keyValue);
    }

}
