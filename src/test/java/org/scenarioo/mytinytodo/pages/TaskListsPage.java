package org.scenarioo.mytinytodo.pages;


import org.openqa.selenium.By;
import org.scenarioo.mytinytodo.components.TaskListContextMenu;
import org.scenarioo.mytinytodo.components.TaskListTab;
import org.scenarioo.mytinytodo.components.TaskListTabBar;
import org.scenarioo.selenium.infrastructure.HtmlElement;
import org.scenarioo.selenium.infrastructure.PageObject;
import org.scenarioo.selenium.infrastructure.components.Button;
import org.scenarioo.selenium.infrastructure.components.ConfirmDialog;
import org.scenarioo.selenium.infrastructure.components.PromptDialog;

public class TaskListsPage extends PageObject {

	private TaskListTabBar listsTabBar = create(TaskListTabBar.class, By.cssSelector("#lists ul"));
	private Button addListButton = create(Button.class, By.cssSelector("#lists div.mtt-tabs-add-button"));
	
	private PromptDialog taskListPrompt = new PromptDialog();
	private ConfirmDialog taskListConfirm = new ConfirmDialog();
	
	public TaskListsPage(HtmlElement element) {
		super(element);
	}

	public void selectTaskList(String title) {
		TaskListTab tabToSelect = listsTabBar.findByTitle(title);
		tabToSelect.select();
	}
	
	public void createTaskList(String title) {
		addListButton.click();
		taskListPrompt.assertMessage("Create new list");
		taskListPrompt.enter(title);
		listsTabBar.assertTabExists(title);
	}
	
	public void deleteSelectedTaskList() {
		TaskListTab taskListTab = listsTabBar.findSelectedTab();
		TaskListContextMenu contextMenu = taskListTab.openContextMenu();
		contextMenu.deleteList();
		taskListConfirm.assertMessage("This will delete current list with all tasks in it.\nAre you sure?");
		taskListConfirm.confirm();
		// TODO Exercise B: assert that the TaskListTab does not exist anymore
	}
}
