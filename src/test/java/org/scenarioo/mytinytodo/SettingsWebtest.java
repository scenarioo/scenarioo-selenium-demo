package org.scenarioo.mytinytodo;

import org.junit.Before;
import org.junit.Test;
import org.scenarioo.mytinytodo.pages.HeaderPage;
import org.scenarioo.mytinytodo.pages.SettingsPage;

public class SettingsWebtest extends AbstractTinyTodoWebTest {
	
	private static final String DEFAULT_TITLE = "My Tiny Todolist";
	private HeaderPage headerPage;
	private SettingsPage settingsPage;
	
	@Before
	public void setup() {
		headerPage = create(HeaderPage.class);
		settingsPage = create(SettingsPage.class);
	}
	
	@Test
	public void configureTitle() {		
		start();	
		headerPage.openSettings();
		settingsPage.changeTitle("My Pretty Fancy Todolist");
		headerPage.assertTitle("My Pretty Fancy Todolist");
	}
	
	@Test
	public void configureTitle_EmptyValueForDefaultTitle() {		
		start();
		headerPage.openSettings();
		settingsPage.changeTitle("");		
		headerPage.assertTitle(DEFAULT_TITLE);
	}
	
}
