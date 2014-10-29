package org.scenarioo.mytinytodo;

import org.junit.Before;
import org.junit.Test;
import org.scenarioo.mytinytodo.base.TinyTodoWebTest;
import org.scenarioo.mytinytodo.pages.EditTaskPage;
import org.scenarioo.mytinytodo.pages.TaskListsPage;
import org.scenarioo.mytinytodo.pages.TasksPage;
import org.scenarioo.mytinytodo.testdata.Task;

public class TaskCreationWebtest extends TinyTodoWebTest {
	
	private TaskListsPage taskListsPage;
	private TasksPage tasksPage;
	private EditTaskPage editTaskPage;
	
	@Before
	public void setup() {
		taskListsPage = create(TaskListsPage.class);
		tasksPage = create(TasksPage.class);
		editTaskPage = create(EditTaskPage.class);
	}

	@Test
	public void addQuickTask() {
		start();
		taskListsPage.selectTaskList("Todo");
		tasksPage.createQuickTask(Task.SIMPLE.getTitle());
		tasksPage.assertTaskExists(Task.SIMPLE);
	}
	
	@Test
	public void addAdvancedTask() {
		start();
		tasksPage.createAdvancedTask(Task.SIMPLE2.getTitle());
		editTaskPage.assertFormPrefilled(Task.SIMPLE2);
		editTaskPage.enter(Task.SIMPLE2_WITHTAGS);
		tasksPage.assertTaskWithTags(Task.SIMPLE2_WITHTAGS);
	}
		
	@Test
	public void addQuickTaskWithSmartSyntax() {
		start();
		tasksPage.createQuickTask("/0/ Simple task 3 /tag1, tag2/");
		tasksPage.assertTaskWithTags(Task.SIMPLE3_WITHTAGS);
	}
}
