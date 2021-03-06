package com.ilham.github.properties.credential;

import com.ilham.github.util.Constants;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

@Slf4j
public class CredentialProperties extends Properties {

  public CredentialProperties() {
    try (InputStream inputStream =
        getClass().getClassLoader().getResourceAsStream(Constants.CREDENTIAL_PROPERTIES_FILE_NAME)) {
      if (inputStream != null) {
        super.load(inputStream);
      } else {
        throw new FileNotFoundException(
            Constants.CREDENTIAL_PROPERTIES_FILE_NAME + " not found in the classpath.");
      }
    } catch (Exception e) {
      log.error("Error: " + e);
    }
  }
}
