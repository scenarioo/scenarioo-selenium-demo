package org.scenarioo.mytinytodo.pages;

import org.openqa.selenium.By;
import org.scenarioo.mytinytodo.components.TaskListTabBar;
import org.scenarioo.selenium.infrastructure.HtmlElement;
import org.scenarioo.selenium.infrastructure.PageObject;
import org.scenarioo.selenium.infrastructure.components.Button;

public class TaskListsPage extends PageObject {

	private TaskListTabBar listsTabBar = create(TaskListTabBar.class, By.cssSelector("#lists ul"));
	private Button addListButton = create(Button.class, By.cssSelector("#lists div.mtt-tabs-add-button"));
	private Button allListsButton = create(Button.class, By.cssSelector("#list_all a"));
	
	public TaskListsPage(HtmlElement element) {
		super(element);
	}

	public void start() {
		getBrowser().navigateTo("http://localhost/mytinytodo/");
		
	}

	public void showTaskList(String title) {
		listsTabBar.selectTab(title);
	}

	public void showAllTaskLists() {
		allListsButton.click();
	}
	
	public void createTaskList(String title) {
		addListButton.click();
	}
}
