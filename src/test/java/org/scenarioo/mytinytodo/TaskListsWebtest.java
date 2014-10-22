package org.scenarioo.mytinytodo;

import org.junit.Before;
import org.junit.Test;
import org.scenarioo.mytinytodo.pages.TasksPage;
import org.scenarioo.mytinytodo.pages.TaskListsPage;
import org.scenarioo.selenium.infrastructure.WebTest;

public class TaskListsWebtest extends WebTest {
	
	private TaskListsPage taskListsPage;
	private TasksPage tasksPage;
	
	@Before
	public void init() {
		taskListsPage = create(TaskListsPage.class);
		tasksPage = create(TasksPage.class);
	}
	
	@Test
	public void createTaskList() {
		taskListsPage.start();
		taskListsPage.createTaskList("Todo 2");
		taskListsPage.showTaskList("Todo 2");
		tasksPage.assertIsEmpty();
	}
}
