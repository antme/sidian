package com.sidian.cfg;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.sidian.util.ApiUtil;

public class CFGManager {
	private static Logger logger = LogManager.getLogger(CFGManager.class);

	private static Properties properties = new Properties();


	public static void setConfiguraion(String configFiles) {
		if (properties.isEmpty()) {

			String files[] = configFiles.split(",");

			for (String file : files) {
				try {
					// load resource from class root path
					properties.load(CFGManager.class.getResourceAsStream("/".concat(file)));
				} catch (IOException e) {
					logger.fatal("Load property file failed: ".concat(file), e);
				}

			}
		}

	}



	public static String OS = System.getProperty("os.name").toLowerCase();

	public static String getProperty(String key) {

		if (isWindows()) {

			if (properties.getProperty(key + "_windows") != null) {
				return properties.getProperty(key + "_windows");
			}
		}
		return properties.getProperty(key);
	}

	
	public static void setProperties(String key, String value) {
		properties.setProperty(key, value);
	}

	public static void remove(String key) {
		properties.remove(key);
	}


	public static boolean isWindows() {

		return (OS.indexOf("win") >= 0);

	}

	public static boolean isMac() {

		return (OS.indexOf("mac") >= 0);

	}

	public static boolean isUnix() {

		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);

	}

	public static boolean isSolaris() {

		return (OS.indexOf("sunos") >= 0);

	}
}
