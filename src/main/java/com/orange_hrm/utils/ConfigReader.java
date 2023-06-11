package com.orange_hrm.utils;

import java.io.*;
import java.util.Properties;

public class ConfigReader {

    static Properties prop;
    static String rootDir = System.getProperty("user.dir");
    static String resourceDir = "src" + File.separator + "main" + File.separator + "resources";

    public static Properties loadProperties(String fileName) {
        prop = new Properties();
        String propFilePath = rootDir + File.separator + resourceDir + File.separator + fileName.toLowerCase() + ".properties";

        try (InputStream ism = new FileInputStream(propFilePath)) {
            prop.load(ism);
        } catch (FileNotFoundException fnfe) {
            System.out.println("FILE IS NOT AVAILABLE AT: " + propFilePath);
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }

        return prop;
    }
}
