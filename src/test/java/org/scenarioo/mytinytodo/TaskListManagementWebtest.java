package org.scenarioo.mytinytodo;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.scenarioo.mytinytodo.base.TinyTodoWebTest;
import org.scenarioo.mytinytodo.pages.TaskListsPage;
import org.scenarioo.mytinytodo.pages.TasksPage;
import org.scenarioo.selenium.infrastructure.db.Dataset;
import org.scenarioo.selenium.infrastructure.db.DatasetDefinition;

public class TaskListManagementWebtest extends TinyTodoWebTest {
	
	private TaskListsPage taskListsPage;
	private TasksPage tasksPage;
	
	@Before
	public void init() {
		taskListsPage = create(TaskListsPage.class);
		tasksPage = create(TasksPage.class);
	}
	
	@Test
	@Ignore("Fails... but why?!")
	public void createTaskList() {
		// TODO Exercise 1: fix me!
		start();
		taskListsPage.createTaskList("Todo 2");		
		tasksPage.assertIsEmpty();
	}
	
	@Dataset(DatasetDefinition.MANY_PREDEFINED_LISTS_AND_TASKS)
	@Test
	public void renameTaskList() {
		start();
		taskListsPage.showTaskList("Todos with spelling mstake");
		taskListsPage.renameSelectedTaskList("Todos without spelling mistake");
	}

	@Dataset(DatasetDefinition.MANY_PREDEFINED_LISTS_AND_TASKS)
	@Test
	public void deleteTaskList() {
		start();
		taskListsPage.showTaskList("Todos to be deleted");		
		taskListsPage.deleteSelectedTaskList();
	}
}
