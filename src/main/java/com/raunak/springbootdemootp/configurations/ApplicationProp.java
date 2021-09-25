package com.raunak.springbootdemootp.configurations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ApplicationProp {
    /**
     * Usage -
     *   // Get individual properties
     *   System.out.println(ApplicationProp.getInstance().getProperty("firstName"));
     *   System.out.println(ApplicationProp.getInstance().getProperty("lastName"));
     *
     *   //All property names
     *   System.out.println(ApplicationProp.getInstance().getAllPropertyNames());
     */

    private static final Logger log = LogManager.getLogger(ApplicationProp.class);
    private final Properties configProp = new Properties();

    private ApplicationProp() {
        //Private constructor to restrict new instances
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        log.info("Reading application properties from the file");
        try {
            configProp.load(in);

            // Required to over-ride application.properties file by Environment/Docker/externally supplied properties.
            Map<String, String> env = System.getenv();
            for (Map.Entry<String, String> entry : env.entrySet()) {
                configProp.put(entry.getKey(), entry.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Bill Pugh Solution for singleton pattern
    private static class LazyHolder {
        private static final ApplicationProp INSTANCE = new ApplicationProp();
    }

    public static ApplicationProp getInstance() {
        return LazyHolder.INSTANCE;
    }

    public String getProperty(String key) {
        return configProp.getProperty(key);
    }

    public Set<String> getAllPropertyNames() {
        return configProp.stringPropertyNames();
    }

    public boolean containsKey(String key) {
        return configProp.containsKey(key);
    }
}
