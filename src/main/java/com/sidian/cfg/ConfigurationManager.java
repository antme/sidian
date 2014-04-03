package com.sidian.cfg;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class ConfigurationManager {

	public static final String DB_NAME = "db_name";

	private static Logger logger = LogManager.getLogger(ConfigurationManager.class);

	private static Properties properties = new Properties();

	public static void setConfiguraion(String configFile) {

		if (properties.isEmpty()) {

			try {
				// load resource from class root path
				properties.load(ConfigurationManager.class.getResourceAsStream("/" + configFile));
			} catch (IOException e) {
				logger.fatal("Load property file failed: ".concat(configFile), e);
			}

		}
	}

	public static String getDbName() {
		return properties.getProperty(DB_NAME);
	}

}
