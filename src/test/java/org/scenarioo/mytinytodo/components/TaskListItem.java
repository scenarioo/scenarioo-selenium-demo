package org.scenarioo.mytinytodo.components;

import org.openqa.selenium.By;
import org.scenarioo.selenium.infrastructure.HtmlElement;
import org.scenarioo.selenium.infrastructure.components.Button;
import org.scenarioo.selenium.infrastructure.components.PageComponent;
import org.scenarioo.selenium.infrastructure.components.TextElement;

public class TaskListItem extends PageComponent {
	
	private TextElement title = create(TextElement.class, By.className("task-title"));
	private Button expandToggle = create(Button.class, By.className("task-toggle"));
	private TextElement note = create(TextElement.class, By.cssSelector(".task-note span"));
//	private TagList tagList = create(TagList.class, By.className("task-tags")); TODO not working yet

	public TaskListItem(HtmlElement element) {
		super(element);
	}

	public String getTitle() {
		return title.getText();
	}
	
	public void editTask() {
		element.doubleClick();
	}
	
	public void expandItem() {
		note.assertIsNotDisplayed();
		expandToggle.click();
	}
	
	public void collapseItem() {
		note.assertIsDisplayed();
		expandToggle.click();
	}
	
	public void assertNote(String text) {
		note.assertText(text);
	}
	
	// TODO not working yet
//	public void assertTags(String[] tags) {
//		for (String tag : tags) {
//			tagList.assertTagExists(tag);
//		}
//	}
}
