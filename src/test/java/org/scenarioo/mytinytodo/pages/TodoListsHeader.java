package org.scenarioo.mytinytodo.pages;

import org.openqa.selenium.By;
import org.scenarioo.selenium.infrastructure.HtmlElement;
import org.scenarioo.selenium.infrastructure.components.Button;
import org.scenarioo.selenium.infrastructure.components.PageComponent;

public class TodoListsHeader extends PageComponent {

	TodoListTabBar tabBar = create(TodoListTabBar.class, By.tagName("ul"));
	Button addButton = create(Button.class, By.cssSelector("div.mtt-tabs-add-button"));
	Button allListsButton = create(Button.class, By.cssSelector("#list_all a"));
	
	public TodoListsHeader(HtmlElement element) {
		super(element);
	}
	
	public void showTodoList(String title) {
		tabBar.selectTab(title);
	}
	
	public void createTodoList(String title) {
		addButton.click();
	}
}
