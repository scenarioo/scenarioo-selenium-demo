package org.scenarioo.mytinytodo.components;

import org.openqa.selenium.By;
import org.scenarioo.selenium.infrastructure.HtmlElement;
import org.scenarioo.selenium.infrastructure.components.Link;
import org.scenarioo.selenium.infrastructure.components.List;

public class TagList extends List<Link> {

	public TagList(HtmlElement element) {
		super(element, Link.class);
	}

	public void assertTagExists(String tag) {
		assertExists(link -> link.getText().equals(tag));
	}
	
	public void selectTag(String tag) {
		find(link -> link.getText().equals(tag)).click();
	}

	@Override
	protected By getItemSelector() {
		return By.tagName("a");
	}
	
}
