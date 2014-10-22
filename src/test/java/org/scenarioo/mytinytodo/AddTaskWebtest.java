package org.scenarioo.mytinytodo;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.scenarioo.mytinytodo.pages.EditTaskPage;
import org.scenarioo.mytinytodo.pages.TasksPage;
import org.scenarioo.mytinytodo.pages.TaskListsPage;
import org.scenarioo.mytinytodo.testdata.Task;
import org.scenarioo.selenium.infrastructure.WebTest;

public class AddTaskWebtest extends WebTest {
	
	private TaskListsPage taskListsPage;
	private TasksPage tasksPage;
	private EditTaskPage editTaskPage;
	
	@Before
	public void init() {
		taskListsPage = create(TaskListsPage.class);
		tasksPage = create(TasksPage.class);
		editTaskPage = create(EditTaskPage.class);
	}

	@Test
	public void addQuickTask() {
		taskListsPage.start();
		taskListsPage.showTaskList("Todo");
		tasksPage.createQuickTask(Task.SIMPLE1.getTitle());
		tasksPage.assertTaskExists(Task.SIMPLE1);
		tasksPage.editTask(Task.SIMPLE1);
		editTaskPage.assertFormPrefilled(Task.SIMPLE1);
		editTaskPage.enter(Task.SIMPLE1_WITHNOTE);
		tasksPage.assertTaskWithNote(Task.SIMPLE1_WITHNOTE);
	}
	
	@Test
	public void addAdvancedTask() {
		taskListsPage.start();
		tasksPage.createAdvancedTask(Task.SIMPLE2.getTitle());
		editTaskPage.assertFormPrefilled(Task.SIMPLE2);
		editTaskPage.enter(Task.SIMPLE2_WITHTAGS);
		tasksPage.assertTaskWithTags(Task.SIMPLE2_WITHTAGS);
	}
}
