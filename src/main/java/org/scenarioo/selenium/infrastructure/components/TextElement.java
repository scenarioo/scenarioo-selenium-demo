package org.scenarioo.selenium.infrastructure.components;

import static org.junit.Assert.assertEquals;

import org.scenarioo.selenium.infrastructure.HtmlElement;

public class TextElement extends PageComponent {

	public TextElement(HtmlElement element) {
		super(element);
	}
	
	public void assertText(String text) {
		assertEquals(text, element.getText());
	}
	
	public String getText() {
		return element.getText();
	}
}
