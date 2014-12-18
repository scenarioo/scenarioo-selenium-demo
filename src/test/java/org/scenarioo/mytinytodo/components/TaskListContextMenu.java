package org.scenarioo.mytinytodo.components;


import org.junit.Assert;
import org.scenarioo.selenium.infrastructure.HtmlElement;
import org.scenarioo.selenium.infrastructure.components.PageComponent;

public class TaskListContextMenu extends PageComponent {
	// TODO Exercise B: use the following line to implement method deleteList()
	// private Button deleteButton = create(Button.class, By.id("btnDeleteList"));
	
	public TaskListContextMenu(HtmlElement element) {
		super(element);
	}
	
	public void deleteList() {
		// TODO Exercise B: implement this
		Assert.fail("not implemented");
	}
}
