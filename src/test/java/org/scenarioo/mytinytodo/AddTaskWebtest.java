package org.scenarioo.mytinytodo;

import org.junit.Before;
import org.junit.Test;
import org.scenarioo.mytinytodo.pages.EditTaskPage;
import org.scenarioo.mytinytodo.pages.OverviewPage;
import org.scenarioo.mytinytodo.testdata.Task;
import org.scenarioo.selenium.infrastructure.WebTest;

public class AddTaskWebtest extends WebTest {
	
	private OverviewPage overviewPage;
	private EditTaskPage editTaskPage;
	
	@Before
	public void init() {
		overviewPage = create(OverviewPage.class);
		editTaskPage = create(EditTaskPage.class);
	}

	@Test
	public void addQuickTask() {
		overviewPage.start();
		overviewPage.showTodoList("Todo");
		overviewPage.createQuickTask(Task.SIMPLE1.getTitle());
		overviewPage.assertTaskExists(Task.SIMPLE1);
		overviewPage.editTask(Task.SIMPLE1);
		editTaskPage.assertFormPrefilled(Task.SIMPLE1);
		editTaskPage.enter(Task.SIMPLE1_WITHNOTE);
		overviewPage.assertTaskWithNote(Task.SIMPLE1_WITHNOTE);
	}
	
	@Test
	public void addAdvancedTask() {
		overviewPage.start();
		overviewPage.createAdvancedTask(Task.SIMPLE2.getTitle());
		editTaskPage.assertFormPrefilled(Task.SIMPLE2);
		editTaskPage.enter(Task.SIMPLE2_WITHTAGS);
		overviewPage.assertTaskWithTags(Task.SIMPLE2_WITHTAGS);
	}
}
