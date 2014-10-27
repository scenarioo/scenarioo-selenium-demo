package org.scenarioo.mytinytodo.pages;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.scenarioo.mytinytodo.testdata.Task;
import org.scenarioo.selenium.infrastructure.HtmlElement;
import org.scenarioo.selenium.infrastructure.PageObject;
import org.scenarioo.selenium.infrastructure.components.Button;
import org.scenarioo.selenium.infrastructure.components.Textfield;

public class EditTaskPage extends PageObject {

	// private SelectBox priority = create(SelectBox.class, By.cssSelector("#taskedit_form select[name=prio]"));
	private Textfield title = create(Textfield.class, By.cssSelector("#taskedit_form input[name=task]"));
	private Textfield note = create(Textfield.class, By.cssSelector("#taskedit_form textarea[name=note]"));
	private Textfield tags = create(Textfield.class, By.cssSelector("#taskedit_form input[name=tags]"));
	
	private Button saveButton = create(Button.class, By.cssSelector("#taskedit_form .form-bottom-buttons input[type=submit]"));

	public EditTaskPage(HtmlElement element) {
		super(element);
	}

	public void enter(Task task) {
		title.setText(task.getTitle());
		note.setText(task.getNote());
		tags.setText(StringUtils.join(task.getTags(), ", "));
		saveButton.click();
	}
	
	public void assertFormPrefilled(Task task) {
		title.assertText(task.getTitle());
		note.assertText(task.getNote());
	}

}
