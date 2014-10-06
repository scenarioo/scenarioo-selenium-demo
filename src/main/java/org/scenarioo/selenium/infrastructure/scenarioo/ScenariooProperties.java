package org.scenarioo.selenium.infrastructure.scenarioo;

import java.io.File;

public class ScenariooProperties {
	
	static {
		BUILD_NAME = "dummy";
	}
	
	public static final File ROOT_DIRECTORY = getRootDirectory();
	public static final String BRANCH = "trunk";
	public static final String BUILD_NAME;
	
	private static File getRootDirectory() {
		File directory = new File("docu");
		if(!directory.exists()) {
			directory.mkdir();
		}
		return directory;
	}
}
