package org.scenarioo.mytinytodo;

import org.junit.Before;
import org.junit.Test;
import org.scenarioo.mytinytodo.pages.TaskListsPage;
import org.scenarioo.mytinytodo.pages.TasksPage;

public class TaskListsWebtest extends AbstractTinyTodoWebTest {
	
	private TaskListsPage taskListsPage;
	private TasksPage tasksPage;
	
	@Before
	public void init() {
		taskListsPage = create(TaskListsPage.class);
		tasksPage = create(TasksPage.class);
	}
	
	@Test
	public void createTaskList() {
		start();
		taskListsPage.createTaskList("Todo 2");
		taskListsPage.showTaskList("Todo 2");
		tasksPage.assertIsEmpty();
	}
	
	@Test
	public void renameTaskList() {
		start();
		taskListsPage.createTaskList("Todo with spelling mstake");
		taskListsPage.showTaskList("Todo with spelling mstake");
		taskListsPage.renameSelectedTaskList("Todo without spelling mistake");
	}
	
	@Test
	public void deleteTaskList() {
		start();
		taskListsPage.createTaskList("Todo to be removed");
		taskListsPage.showTaskList("Todo to be removed");
		taskListsPage.deleteSelectedTaskList();
	}
}
