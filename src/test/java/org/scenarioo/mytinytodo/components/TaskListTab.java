package org.scenarioo.mytinytodo.components;


import org.openqa.selenium.By;
import org.scenarioo.selenium.infrastructure.HtmlElement;
import org.scenarioo.selenium.infrastructure.components.Button;
import org.scenarioo.selenium.infrastructure.components.PageComponent;

public class TaskListTab extends PageComponent {
	
	private static final String CSS_CLASS_SELECTED = "mtt-tabs-selected";
	
	private Button title = create(Button.class, By.tagName("span"));
	private Button contextMenuToggle = create(Button.class, By.cssSelector("div.list-action"));
	private TaskListContextMenu contextMenu = createInGlobalScope(TaskListContextMenu.class, By.cssSelector("#listmenucontainer ul"));
	
	public TaskListTab(HtmlElement element) {
		super(element);
	}
	
	public String getTitle() {
		return title.getText();
	}
	
	public void assertTitle(String title) {
		this.title.assertText(title);
	}

	public void select() {
		title.click();
		assertIsSelected();
	}

	public boolean isSelected() {
		return element.getCssClasses().contains(CSS_CLASS_SELECTED);
	}
	
	public void assertIsSelected() {
		element.assertCssClass(CSS_CLASS_SELECTED);
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
