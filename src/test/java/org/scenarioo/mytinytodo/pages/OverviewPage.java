package org.scenarioo.mytinytodo.pages;

import org.openqa.selenium.By;
import org.scenarioo.selenium.infrastructure.HtmlElement;
import org.scenarioo.selenium.infrastructure.components.Button;
import org.scenarioo.selenium.infrastructure.components.Textfield;
import org.scenarioo.selenium.infrastructure.pages.WebPage;

public class OverviewPage extends WebPage {

	TodoListTabBar tabBar = getBrowser().create(TodoListTabBar.class, By.cssSelector("#lists ul"));
	Button addButton = getBrowser().create(Button.class, By.cssSelector("#lists div.mtt-tabs-add-button"));
	Button allListsButton = getBrowser().create(Button.class, By.cssSelector("#list_all a"));
	
	private Textfield addTaskTextfield = getBrowser().create(Textfield.class, By.id("task"));
	private Button addTaskButton = getBrowser().create(Button.class, By.id("newtask_submit")); // TODO hack, it's not a button
	
	public void start() {
		getBrowser().navigateTo("http://localhost/mytinytodo/");
	}

	public void showTodoList(String title) {
		tabBar.selectTab(title);
	}
	
	public void createTodoList(String title) {
		addButton.click();
		// TODO enter name and confirm
	}

	public void addQuickTask(String taskName) {
		addTaskTextfield.setText(taskName);
		addTaskButton.click();
	}
	
	public void assertTaskExists(String taskName) {
		
	}

	public void openAdvancedTask() {
		// TODO Auto-generated method stub
		
	}
}
