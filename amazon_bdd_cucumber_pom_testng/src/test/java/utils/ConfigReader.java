
package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties P = new Properties();

    // Load configuration file when the class is initialized
    static {

        try (FileInputStream in =
                new FileInputStream(
                        "src/test/resources/config.properties"
                )) {

            P.load(in);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Cannot load config.properties",
                    e
            );
        }
    }

    // Read configuration value as String
    public static String getProperty(String key) {

        String value = P.getProperty(key);

        if (value == null) {

            throw new RuntimeException(
                    "Missing config: " + key
            );
        }

        return value.trim();
    }

    // Read configuration value as Integer
    public static int getIntProperty(String key) {

        return Integer.parseInt(getProperty(key));
    }
}
