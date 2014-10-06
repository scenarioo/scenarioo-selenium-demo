package org.scenarioo.mytinytodo.pages;

import org.openqa.selenium.By;
import org.scenarioo.selenium.infrastructure.HtmlElement;
import org.scenarioo.selenium.infrastructure.components.Button;
import org.scenarioo.selenium.infrastructure.components.PageComponent;
import org.scenarioo.selenium.infrastructure.components.TextElement;

public class TodoListTab extends PageComponent {
	private Button title = create(Button.class, By.tagName("span"));
//	private ContextMenu menu = create(ContextMenu.class, By.tagName("ul"));
	
	public TodoListTab(HtmlElement element) {
		super(element);
	}
	
	public String getTitle() {
		return title.getText();
	}

	public void select() {
		title.click();
	}
}
