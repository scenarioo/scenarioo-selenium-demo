package org.scenarioo.mytinytodo.components;

import org.scenarioo.selenium.infrastructure.HtmlElement;
import org.scenarioo.selenium.infrastructure.components.List;

public class TodoListTabBar extends List<TodoListTab> {

	public TodoListTabBar(HtmlElement element) {
		super(element, TodoListTab.class);
	}

	public void selectTab(String title) {
		TodoListTab tabToSelect = find(tab -> tab.getTitle().equals(title));
		tabToSelect.select();
	}
	
}
