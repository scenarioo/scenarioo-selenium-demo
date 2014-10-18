package org.scenarioo.selenium.infrastructure.scenarioo;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.scenarioo.api.ScenarioDocuWriter;
import org.scenarioo.model.docu.entities.Branch;
import org.scenarioo.model.docu.entities.Build;
import org.scenarioo.model.docu.entities.Status;

/**
 * You would usually do this in a build file to have more options available. 
 *
 */
public class ScenariooProperties {
	
	public static final File ROOT_DIRECTORY;
	public static final String BRANCH;;
	private static final Date BUILD_DATE;
	public static final String BUILD_NAME;
	
	static {
		BUILD_DATE = new Date();
		BRANCH = "trunk";
		ROOT_DIRECTORY = initRootDirectory();
		BUILD_NAME = createBuildName();
		
		writeBuildDescription(Status.SUCCESS);
	}
	
	private static File initRootDirectory() {
		File directory = new File("docu");
		if(!directory.exists()) {
			directory.mkdir();
		}
		return directory;
	}
	
	private static String createBuildName() {
		return new SimpleDateFormat("yyyy-MM-dd_hhmmss").format(BUILD_DATE);
	}

	public static void writeSuccessBuildDescription() {
		writeBuildDescription(Status.SUCCESS);
	}
	
	public static void writeFailedBuildDescription() {
		writeBuildDescription(Status.FAILED);
	}

	private static void writeBuildDescription(Status status) {
		ScenarioDocuWriter writer = new ScenarioDocuWriter(ROOT_DIRECTORY, BRANCH, BUILD_NAME);
		writer.saveBranchDescription(new Branch(BRANCH));
		Build build = new Build(BUILD_NAME);
		build.setStatus(status);
		build.setDate(BUILD_DATE);
		writer.saveBuildDescription(build);
	}
}
