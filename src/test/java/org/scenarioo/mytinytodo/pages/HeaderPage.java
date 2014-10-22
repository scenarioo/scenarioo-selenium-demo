package org.scenarioo.mytinytodo.pages;

import org.openqa.selenium.By;
import org.scenarioo.selenium.infrastructure.HtmlElement;
import org.scenarioo.selenium.infrastructure.PageObject;
import org.scenarioo.selenium.infrastructure.components.Link;
import org.scenarioo.selenium.infrastructure.components.TextElement;

public class HeaderPage extends PageObject {

	private TextElement title = create(TextElement.class, By.tagName("h2"));
	private Link settingsLink = create(Link.class, By.id("settings"));

	
	public HeaderPage(HtmlElement element) {
		super(element);
	}
	
	public void openSettings() {
		settingsLink.click();
	}
	
	public void assertTitle(String expectedTitle) {
		title.assertText(expectedTitle);
	}
	
}
