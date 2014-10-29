package org.scenarioo.mytinytodo.base;

import org.scenarioo.selenium.infrastructure.WebTest;

public class TinyTodoWebTest extends WebTest {

	protected void start() {
		getBrowser().navigateTo("http://localhost/mytinytodo/");
	}
	
}
