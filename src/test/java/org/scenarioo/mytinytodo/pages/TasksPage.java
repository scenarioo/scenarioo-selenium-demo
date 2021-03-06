package org.scenarioo.mytinytodo.pages;

import org.openqa.selenium.By;
import org.scenarioo.mytinytodo.components.TaskList;
import org.scenarioo.mytinytodo.components.TaskListItem;
import org.scenarioo.mytinytodo.testdata.Task;
import org.scenarioo.selenium.infrastructure.HtmlElement;
import org.scenarioo.selenium.infrastructure.PageObject;
import org.scenarioo.selenium.infrastructure.components.Button;
import org.scenarioo.selenium.infrastructure.components.Textfield;

public class TasksPage extends PageObject {

	private Textfield addTaskTextfield = create(Textfield.class, By.id("task"));
	private Button addQuickTaskButton = create(Button.class, By.id("newtask_submit"));
	private Button addAdvancedTaskButton = create(Button.class, By.id("newtask_adv"));
	
	private TaskList taskList = create(TaskList.class, By.id("tasklist"));
	
	public TasksPage(HtmlElement element) {
		super(element);
	}
	
	public void createQuickTask(String title) {
		addTaskTextfield.setText(title);
		addQuickTaskButton.click();
	}
	
	public void createAdvancedTask(String title) {
		addTaskTextfield.setText(title);
		addAdvancedTaskButton.click();
	}
	
	public void assertTaskExists(Task task) {
		taskList.assertTaskExists(task.getTitle());
	}
	
	public void assertTaskWithNote(Task task) {
		TaskListItem taskListItem = taskList.find(task.getTitle());
		taskListItem.expandItem();
		taskListItem.assertNote(task.getNote());
		taskListItem.collapseItem();
	}

	public void openTaskForEdit(Task task) {
		TaskListItem taskListItem = taskList.find(task.getTitle());
		taskListItem.editTask();
	}

	public void assertTaskWithTags(Task task) {
		TaskListItem taskListItem = taskList.find(task.getTitle());
		taskListItem.assertTags(task.getTags());
	}

	public void assertIsEmpty() {
		taskList.assertIsEmpty();
	}
}
