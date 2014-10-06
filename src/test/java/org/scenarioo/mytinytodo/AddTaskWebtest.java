package org.scenarioo.mytinytodo;

import org.junit.Test;
import org.scenarioo.mytinytodo.pages.AdvancedTaskPage;
import org.scenarioo.mytinytodo.pages.OverviewPage;
import org.scenarioo.selenium.infrastructure.WebTest;

public class AddTaskWebtest extends WebTest {
	
	private OverviewPage overviewPage = new OverviewPage();
	private AdvancedTaskPage advancedTaskPage = new AdvancedTaskPage();
	
	@Test
	public void addQuickTask() {
		overviewPage.start();
		overviewPage.addQuickTask("Einleitung");
	}
	
	@Test
	public void addAdvancedTask() {
		// TODO
		overviewPage.openAdvancedTask();
		advancedTaskPage.enter();	// TODO
	}
}
