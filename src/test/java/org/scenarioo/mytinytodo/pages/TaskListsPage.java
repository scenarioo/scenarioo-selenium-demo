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
	private Button allListsButton = create(Button.class, By.cssSelector("#list_all a"));
	
	private PromptDialog taskListPrompt = new PromptDialog();
	private ConfirmDialog taskListConfirm = new ConfirmDialog();
	
	public TaskListsPage(HtmlElement element) {
		super(element);
	}

	public void showTaskList(String title) {
		TaskListTab tabToSelect = listsTabBar.findByTitle(title);
		tabToSelect.select();
	}

	public void showAllTaskLists() {
		allListsButton.click();
	}
	
	public void createTaskList(String title) {
		addListButton.click();
		taskListPrompt.assertMessage("Create new list");
		taskListPrompt.enter(title);
		listsTabBar.assertTabExists(title);
	}
	
	public void renameTaskList(String oldTitle, String newTitle) {
		TaskListTab taskListTab = listsTabBar.findByTitle(oldTitle);
		taskListTab.select();
		TaskListContextMenu contextMenu = taskListTab.openContextMenu();
		contextMenu.renameList();
		taskListPrompt.assertMessage("Rename list");
		taskListPrompt.enter(newTitle);
		listsTabBar.assertTabExists(newTitle);
	}
	
	public void deleteTaskList(String title) {
		TaskListTab taskListTab = listsTabBar.findByTitle(title);
		taskListTab.select();
		TaskListContextMenu contextMenu = taskListTab.openContextMenu();
		contextMenu.deleteList();
		taskListConfirm.assertMessage("This will delete current list with all tasks in it.\nAre you sure?");
		taskListConfirm.confirm();
		listsTabBar.assertTabDoesNotExist(title);
	}
}
