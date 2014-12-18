package org.scenarioo.mytinytodo.components;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.scenarioo.selenium.infrastructure.HtmlElement;
import org.scenarioo.selenium.infrastructure.components.Button;
import org.scenarioo.selenium.infrastructure.components.PageComponent;

public class TaskListContextMenu extends PageComponent {
	private Button renameButton = create(Button.class, By.id("btnRenameList"));
	// TODO Exercise 1.2: use the following line to implement method deleteList()
	// private Button deleteButton = create(Button.class, By.id("btnDeleteList"));
	
	public TaskListContextMenu(HtmlElement element) {
		super(element);
	}

	public void renameList() {
		renameButton.click();
	}
	
	public void deleteList() {
		// TODO Exercise 1.2: implement this
		Assert.fail("not implemented");
	}
}
