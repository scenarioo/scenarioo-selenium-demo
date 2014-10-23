package org.scenarioo.mytinytodo.components;

import org.scenarioo.selenium.infrastructure.HtmlElement;
import org.scenarioo.selenium.infrastructure.components.List;

public class TaskListTabBar extends List<TaskListTab> {

	public TaskListTabBar(HtmlElement element) {
		super(element, TaskListTab.class);
	}
	
	public void assertTabExists(String title) {
		assertItemExists(tab -> tab.getTitle().equals(title));
	}
	
	public void assertTabDoesNotExist(String title) {
		assertItemDoesNotExist(tab -> tab.getTitle().equals(title));
	}
	
	public TaskListTab findByTitle(String title) {
		return find(tab -> tab.getTitle().equals(title));
	}
	
}
