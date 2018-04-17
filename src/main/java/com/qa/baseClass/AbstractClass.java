package com.qa.baseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class AbstractClass {
	public Properties prop;
	
	public AbstractClass() {

		try {
			File file = new File(System.getProperty("user.dir") + "/src/main/java/com/qa/config/Config.properties");
			prop = new Properties();
			FileInputStream ip = new FileInputStream(file);
			prop.load(ip);
		} catch (Exception e) {
			System.out.println("unable to read the property file");
			e.printStackTrace();
		}

	}

}
