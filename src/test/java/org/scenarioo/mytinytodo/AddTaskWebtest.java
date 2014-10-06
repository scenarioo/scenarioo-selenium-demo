package org.scenarioo.mytinytodo;

import org.junit.Before;
import org.junit.Test;
import org.scenarioo.mytinytodo.pages.AdvancedTaskPage;
import org.scenarioo.mytinytodo.pages.OverviewPage;
import org.scenarioo.selenium.infrastructure.WebTest;

public class AddTaskWebtest extends WebTest {
	
	private OverviewPage overviewPage;
	private AdvancedTaskPage advancedTaskPage;
	
	@Before
	public void init() {
		overviewPage = create(OverviewPage.class);
		advancedTaskPage = create(AdvancedTaskPage.class);
	}

	
	@Test
	public void addQuickTask() {
		overviewPage.start();
		overviewPage.showTodoList("Todo");
		overviewPage.addQuickTask("Einleitung");
		overviewPage.assertTaskExists("Einleitung");
	}
	
	@Test
	public void addAdvancedTask() {
		// TODO
		overviewPage.openAdvancedTask();
		advancedTaskPage.enter();	// TODO
	}
}
