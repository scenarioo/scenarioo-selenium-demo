package org.scenarioo.mytinytodo.pages;

import org.scenarioo.selenium.infrastructure.pages.WebPage;

public class OverviewPage extends WebPage {
	public void start() {
		getBrowser().navigateTo("http://localhost/mytinytodo/");
	}
}
