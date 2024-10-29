package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataUtils {
    public final static String TEST_DATA_PATH = "src/test/resources/TestData/";


    public static String getPropertyValue(String fileName, String key) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(TEST_DATA_PATH + fileName + ".properties"));
        return properties.getProperty(key);
    }
}
