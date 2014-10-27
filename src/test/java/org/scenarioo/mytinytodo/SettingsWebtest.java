package org.scenarioo.mytinytodo;

import org.junit.Before;
import org.junit.Test;
import org.scenarioo.mytinytodo.pages.HeaderPage;
import org.scenarioo.mytinytodo.pages.SettingsPage;

public class SettingsWebtest extends AbstractTinyTodoWebTest {
	
	private HeaderPage headerPage;
	private SettingsPage settingsPage;
	
	@Before
	public void setup() {
		headerPage = create(HeaderPage.class);
		settingsPage = create(SettingsPage.class);
	}
	
	@Test
	public void changeTitle() {
		start();
		headerPage.assertTitle("My Tiny Todolist");
		headerPage.openSettings();
		settingsPage.changeTitle("My Pretty Fancy Todolist");
		headerPage.assertTitle("My Pretty Fancy Todolist");

		headerPage.openSettings();
		settingsPage.changeTitle("My Tiny Todolist");
		headerPage.assertTitle("My Tiny Todolist");
	}

	
}
