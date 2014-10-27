package org.scenarioo.mytinytodo;

import org.scenarioo.selenium.infrastructure.WebTest;

public class AbstractTinyTodoWebTest extends WebTest {

	protected void start() {
		getBrowser().navigateTo("http://localhost/mytinytodo/");
	}
	
}
