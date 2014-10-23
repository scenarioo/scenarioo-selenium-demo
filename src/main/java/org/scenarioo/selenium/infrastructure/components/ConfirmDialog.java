package org.scenarioo.selenium.infrastructure.components;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.Alert;
import org.scenarioo.selenium.infrastructure.BrowserResource;

public class ConfirmDialog {

	public void assertMessage(String message) {
		assertEquals(message, getAlert().getText());
	}
	
	public void confirm() {
		getAlert().accept();
	}
	
	public void cancel() {
		getAlert().dismiss();
	}
	
	private Alert getAlert() {
		return BrowserResource.getBrowser().switchToAlert();
	}
}
