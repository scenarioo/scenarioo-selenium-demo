package org.scenarioo.mytinytodo.components;


import org.openqa.selenium.By;
import org.scenarioo.selenium.infrastructure.HtmlElement;
import org.scenarioo.selenium.infrastructure.components.Button;
import org.scenarioo.selenium.infrastructure.components.PageComponent;

public class TaskListTab extends PageComponent {
	private Button title = create(Button.class, By.tagName("span"));
	private Button contextMenuToggle = create(Button.class, By.cssSelector("div.list-action"));
	private TaskListContextMenu contextMenu = createInGlobalScope(TaskListContextMenu.class, By.cssSelector("#listmenucontainer ul"));
	
	public TaskListTab(HtmlElement element) {
		super(element);
	}
	
	public String getTitle() {
		return title.getText();
	}

	public void select() {
		title.click();
		assertIsSelected();
	}
	
	public void assertIsSelected() {
		element.assertCssClass("mtt-tabs-selected");
	}
	
	public TaskListContextMenu openContextMenu() {
		contextMenuToggle.click();
		contextMenu.assertIsDisplayed();
		return contextMenu;
	}
	
	public void closeContextMenu() {
		contextMenuToggle.click();
		contextMenu.assertIsNotDisplayed();
	}

}
