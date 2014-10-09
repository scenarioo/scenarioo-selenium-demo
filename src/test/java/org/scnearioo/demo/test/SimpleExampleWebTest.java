package org.scnearioo.demo.test;

import org.junit.Before;
import org.junit.Test;
import org.scenarioo.selenium.infrastructure.WebTest;
import org.scnearioo.demo.test.pages.StartPage;

public class SimpleExampleWebTest extends WebTest {
	
	private StartPage startPage;
	
	@Before
	public void setUp() {
		startPage = create(StartPage.class);
	}
	
	@Test
	public void buyASimpleProduct() {		
		
		startPage.startWebShopUnauthenticated();	
		// mainPageNavigation.expectNumberOfItemsInBasket(0);
		
		startPage.selectHotSaucesCatalogue();		
		
		// hotSaucesCatalogue.expectFirstSauceIsNewSuddenDeathSauce();
		
		// hotSaucesCatalogue.putFirstSauceIntoBasket();
		// mainPageNavigation.expectNumberOfItemsInBasket(1);
				
		// mainPageNavigation.checkout();
		
		
	}

}
