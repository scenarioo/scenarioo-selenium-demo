package org.scenarioo.mytinytodo;

import org.junit.Before;
import org.junit.Test;
import org.scenarioo.mytinytodo.pages.EditTaskPage;
import org.scenarioo.mytinytodo.pages.TasksPage;
import org.scenarioo.mytinytodo.pages.TaskListsPage;
import org.scenarioo.mytinytodo.testdata.Task;

public class TaskManagementWebtest extends AbstractTinyTodoWebTest {
	
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
	public void editTask() {
		start();
		taskListsPage.showTaskList("Todo");
		tasksPage.createQuickTask(Task.SIMPLE1.getTitle());
		tasksPage.editTask(Task.SIMPLE1);
		editTaskPage.assertFormPrefilled(Task.SIMPLE1);
		editTaskPage.enter(Task.SIMPLE1_WITHNOTE);
		tasksPage.assertTaskWithNote(Task.SIMPLE1_WITHNOTE);
	}

}
