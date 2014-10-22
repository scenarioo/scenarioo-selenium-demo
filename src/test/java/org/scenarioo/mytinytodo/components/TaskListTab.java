package org.scenarioo.mytinytodo.components;


import org.openqa.selenium.By;
import org.scenarioo.selenium.infrastructure.HtmlElement;
import org.scenarioo.selenium.infrastructure.components.Button;
import org.scenarioo.selenium.infrastructure.components.PageComponent;

public class TaskListTab extends PageComponent {
	private Button title = create(Button.class, By.tagName("span"));
//	private ContextMenu menu = create(ContextMenu.class, By.tagName("ul"));
	
	public TaskListTab(HtmlElement element) {
		super(element);
	}
	
	public String getTitle() {
		return title.getText();
	}

	public void select() {
		title.click();
	}

}
