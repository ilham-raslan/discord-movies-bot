package com.ilham.github.properties.application;

import com.ilham.github.util.Constants;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class ApplicationProperties extends Properties {

  public ApplicationProperties() {
    try (InputStream inputStream =
        getClass().getClassLoader().getResourceAsStream(Constants.APPLICATION_PROPERTIES_FILE_NAME)) {
      if (inputStream != null) {
        super.load(inputStream);
      } else {
        throw new FileNotFoundException(
            Constants.APPLICATION_PROPERTIES_FILE_NAME + " not found in the classpath.");
      }
    } catch (Exception e) {
      log.error("Error: " + e);
    }
  }
}
