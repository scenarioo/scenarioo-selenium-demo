package org.scnearioo.demo.test;

import org.junit.Test;
import org.scenarioo.selenium.infrastructure.WebTest;
import org.scnearioo.demo.test.pages.StartPage;

public class SimpleExampleWebTest extends WebTest {
	
	private StartPage startPage = new StartPage();	
	
	@Test
	public void buyASimpleProduct() {		
		
		startPage.startWebShopUnauthenticated();		
		
	}

}
