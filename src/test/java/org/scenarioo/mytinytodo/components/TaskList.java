package org.scenarioo.mytinytodo.components;

import org.scenarioo.selenium.infrastructure.HtmlElement;
import org.scenarioo.selenium.infrastructure.components.List;

public class TaskList extends List<TaskListItem>{

	public TaskList(HtmlElement element) {
		super(element, TaskListItem.class);
	}

	public void assertTaskExists(String title) {
		assertItemExists(taskItem -> taskItem.getTitle().equals(title));
	}
	
	public TaskListItem find(String title) {
		return find(taskItem -> taskItem.getTitle().equals(title));
	}
}
