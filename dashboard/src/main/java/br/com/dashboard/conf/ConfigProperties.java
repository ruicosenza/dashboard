package br.com.dashboard.conf;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigProperties {

	public Properties getProperty() {

        try {
            java.util.Properties prop = new java.util.Properties();
            String path = "/home/monitor/interc2.properties";

            FileInputStream file = new FileInputStream(path);

            prop.load(file);
            file.close();

            return prop;

        } catch (Exception e) {
            Logger.getLogger(ConfigProperties.class.getName()).log(Level.SEVERE, null, e);
        }

        return null;
    }
}