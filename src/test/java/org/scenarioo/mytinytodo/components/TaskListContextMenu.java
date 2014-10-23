package org.scenarioo.mytinytodo.components;

import org.openqa.selenium.By;
import org.scenarioo.selenium.infrastructure.HtmlElement;
import org.scenarioo.selenium.infrastructure.components.Button;
import org.scenarioo.selenium.infrastructure.components.PageComponent;

public class TaskListContextMenu extends PageComponent {
	
	private Button renameButton = create(Button.class, By.id("btnRenameList"));
	private Button deleteButton = create(Button.class, By.id("btnDeleteList"));
	
	public TaskListContextMenu(HtmlElement element) {
		super(element);
	}

	public void renameList() {
		renameButton.click();
	}
	
	public void deleteList() {
		deleteButton.click();
	}
}
