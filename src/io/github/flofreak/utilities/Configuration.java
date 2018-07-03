/*
 * Configuration
 * v1.0
 * 02. Juli 2018
 * Copyright (C) 2018 Florian Warnke
 * All rights reserved.
 */
package io.github.flofreak.utilities;

import java.io.IOException;
import java.util.Properties;

/**
 * Reads the configuration file
 *
 * @author florian.warnke
 * @version v1.0
 */
public class Configuration {

    private final Properties properties;

    /**
     * The configuration file is read and saved
     */
    public Configuration() {
        properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("config/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the key from the property file
     * @param key the key which should be read
     * @return (String) the value from the key
     */
    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
