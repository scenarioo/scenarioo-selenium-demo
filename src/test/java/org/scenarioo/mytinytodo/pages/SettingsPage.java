package org.scenarioo.mytinytodo.pages;

import org.openqa.selenium.By;
import org.scenarioo.selenium.infrastructure.HtmlElement;
import org.scenarioo.selenium.infrastructure.PageObject;
import org.scenarioo.selenium.infrastructure.components.Button;
import org.scenarioo.selenium.infrastructure.components.Textfield;

public class SettingsPage extends PageObject {

	private Textfield titleField = create(Textfield.class, By.cssSelector("#settings_form input[name=title]"));
	private Button saveButton = create(Button.class, By.cssSelector("#settings_form .form-buttons input[type=submit]"));

	
	public SettingsPage(HtmlElement element) {
		super(element);
	}
	
	public void changeTitle(String title) {
		titleField.setText(title);
		save();
	}

	private void save() {
		saveButton.click();
		// wait until the settings screen disappears
		saveButton.waitUntilElementDoesNotExist();
	}
	
}
