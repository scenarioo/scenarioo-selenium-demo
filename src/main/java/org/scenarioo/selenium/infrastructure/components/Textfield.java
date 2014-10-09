package org.scenarioo.selenium.infrastructure.components;

import org.scenarioo.selenium.infrastructure.HtmlElement;

public class Textfield extends PageComponent {

	public Textfield(HtmlElement element) {
		super(element);
	}
	
	public void setText(String text) {
		element.clear();
		element.sendKeys(text);
	}
}
