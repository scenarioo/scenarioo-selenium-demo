package org.scenarioo.mytinytodo.pages;

import org.scenarioo.selenium.infrastructure.HtmlElement;
import org.scenarioo.selenium.infrastructure.components.List;
import org.scenarioo.selenium.infrastructure.components.TextElement;

public class ContextMenu extends List<TextElement> {
	
	public ContextMenu(HtmlElement element) {
		super(element, TextElement.class);
	}

}
