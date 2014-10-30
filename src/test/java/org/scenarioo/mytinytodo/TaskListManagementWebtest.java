package org.scenarioo.mytinytodo;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.scenarioo.mytinytodo.base.TinyTodoWebTest;
import org.scenarioo.mytinytodo.pages.TaskListsPage;
import org.scenarioo.mytinytodo.pages.TasksPage;

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
		// TODO Exercise A: fix me!
		start();
		taskListsPage.createTaskList("Todo 2");
		tasksPage.assertIsEmpty();
	}
	
	@Test
	@Ignore("Not implemented")
	public void deleteTaskList() {
		// TODO Exercise B: create a list and delete it
	}
	
	// TODO Exercise C: add a test for renaming of task lists
}
