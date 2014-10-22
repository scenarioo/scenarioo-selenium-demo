package org.scenarioo.mytinytodo.testdata;

public enum Task {
	SIMPLE1("Simple task 1", ""), 
	SIMPLE1_WITHNOTE("Simple task 1 with a note", "A note"), 
	SIMPLE2("Simple task 2", ""), 
	SIMPLE2_WITHTAGS("Simple task 2", "", "tagA", "tagB"),
	SIMPLE3_WITHTAGS("Simple task 3", "", "tag1", "tag2");
	
	
	private String title;
	private String note;
	private String[] tags;
	
	private Task(String title, String note, String... tags) {
		this.title = title;
		this.note = note;
		this.tags = tags;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getNote() {
		return note;
	}

	public String[] getTags() {
		return tags;
	}

}
