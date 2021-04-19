package com.ilham.github.properties.application;

import com.ilham.github.util.Constants;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class ApplicationProperties implements com.ilham.github.properties.Properties {
    private Properties properties;

    public ApplicationProperties() {
        this.properties = new Properties();

        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(Constants.applicationPropertiesFileName)) {
            if (inputStream != null) {
                this.properties.load(inputStream);
            }
            else {
                throw new FileNotFoundException(Constants.applicationPropertiesFileName + " not found in the classpath.");
            }
        }
        catch (Exception e) {
            log.error("Error: " + e);
        }
    }

    public String getPropertyByName(String name) {
        return this.properties.getProperty(name);
    }
}
