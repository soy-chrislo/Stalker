package com.soychristian.stalker.exceptions;

import java.io.IOException;

public class BadFormatConfigurationFileException extends IOException {
    private final String configurationFilename;
    private final String configurationPath;
    public BadFormatConfigurationFileException(String  configurationFilename, String configurationPath){
        this.configurationPath = configurationPath;
        this.configurationFilename = configurationFilename;
    }

    public String getConfigurationFilename() {
        return configurationFilename;
    }

    public String getConfigurationPath() {
        return configurationPath;
    }
}