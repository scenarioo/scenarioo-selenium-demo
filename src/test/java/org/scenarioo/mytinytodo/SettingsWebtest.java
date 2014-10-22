package org.scenarioo.mytinytodo;

import org.junit.Before;
import org.junit.Test;
import org.scenarioo.mytinytodo.pages.HeaderPage;
import org.scenarioo.mytinytodo.pages.SettingsPage;
import org.scenarioo.selenium.infrastructure.WebTest;

public class SettingsWebtest extends WebTest {
	
	private HeaderPage headerPage;
	private SettingsPage settingsPage;
	
	@Before
	public void init() {
		headerPage = create(HeaderPage.class);
		settingsPage = create(SettingsPage.class);

		getBrowser().navigateTo("http://localhost/mytinytodo/");
	}
	
	@Test
	public void changeTitle() {
		headerPage.assertTitle("My Tiny Todolist");
		headerPage.openSettings();
		settingsPage.changeTitle("My Pretty Fancy Todolist");
		headerPage.assertTitle("My Pretty Fancy Todolist");

		headerPage.openSettings();
		settingsPage.changeTitle("My Tiny Todolist");
		headerPage.assertTitle("My Tiny Todolist");
	}

	
}
