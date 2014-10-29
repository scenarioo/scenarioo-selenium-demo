package org.scenarioo.selenium.infrastructure.db;

/**
 * Predefined dataset definitions that can be used.
 * 
 * Per default the DEFAULT is used.
 * 
 * Each dataset definition refers to a dataset file with inital database setup that can be used for webtests.
 * 
 * The dataset files are stored under src/main/resources/datasets
 * 
 * Each dataset 
 * @author dev
 *
 */
public enum DatasetDefinition {
	
	DEFAULT("default-dataset.xml"),
	EMPTY("empty-dataset.xml"),
	MANY_PREDEFINED_LISTS_AND_TASKS("large-dataset.xml");
	
	private String filename;
	
	private DatasetDefinition(String datasetFileName) {
		this.filename = datasetFileName;
	}

	public String getFilename() {
		return filename;
	}
	
}
