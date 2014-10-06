package org.scenarioo.mytinytodo;

import org.junit.Test;
import org.scenarioo.mytinytodo.pages.OverviewPage;
import org.scenarioo.selenium.infrastructure.WebTest;

public class AddTaskWebtest extends WebTest {
	
	private OverviewPage overviewPage = new OverviewPage();
	
	
	@Test
	public void addQuickTask() {
		overviewPage.start();
	}
}
