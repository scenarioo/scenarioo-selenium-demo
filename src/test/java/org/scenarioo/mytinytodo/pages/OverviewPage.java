package org.scenarioo.mytinytodo.pages;

import org.openqa.selenium.By;
import org.scenarioo.selenium.infrastructure.components.Button;
import org.scenarioo.selenium.infrastructure.components.Textfield;
import org.scenarioo.selenium.infrastructure.pages.WebPage;

public class OverviewPage extends WebPage {
	
	private Textfield addTaskTextfield = getBrowser().create(Textfield.class, By.id("task"));
	private Button addTaskButton = getBrowser().create(Button.class, By.id("newtask_submit")); // TODO hack, it's not a button
	
	public void start() {
		getBrowser().navigateTo("http://localhost/mytinytodo/");
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
