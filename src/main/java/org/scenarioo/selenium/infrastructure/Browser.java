/* Copyright (c) 2014, scenarioo.org Development Team
 * All rights reserved.
 *
 * See https://github.com/scenarioo?tab=members
 * for a complete list of contributors to this project.
 *
 * Redistribution and use of the Scenarioo Examples in source and binary forms,
 * with or without modification, are permitted provided that the following
 * conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright notice, this
 *   list of conditions and the following disclaimer in the documentation and/or
 *   other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 * ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.scenarioo.selenium.infrastructure;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.scenarioo.selenium.infrastructure.scenarioo.WebDriverListenerAdapter;

/**
 * Abstraction of the Selenium API to access the browser window inside which the current web test is running.
 * 
 * The Browser together with {@link HtmlElement} encapsulates the Selenium API to have an additional abstraction layer for cross cutting concerns and abstraction of the concrete ui testing toolkit used.
 * 
 * Based on this API all page components and page objects have to be built to use as building blocks for the tests.
 */
public class Browser {
	
	private WebDriver driver;
	
	/**
	 * Get a browser with a new selenium web driver to run your tests in a firefox browser.
	 */
	public static Browser createFirefoxDriver() {
		EventFiringWebDriver eventFiringDriver = new EventFiringWebDriver(new FirefoxDriver());
		eventFiringDriver.register(new WebDriverListenerAdapter());
		return new Browser(eventFiringDriver);
	}
	
	public Browser(WebDriver driver) {
		this.driver = driver;
	}
	
	/**
	 * Navigate to an URL in browser.
	 * @param url the url to load.
	 * 
	 * see {@link WebDriver#get(String)}.
	 */
	public void navigateTo(final String url) {
		driver.get(url);
	}
	
	public void refresh() {
		driver.navigate().to(driver.getCurrentUrl());
	}

	public void back() {
		driver.navigate().back();
	}
	
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}
	
	public Actions createActions() {
		return new Actions(driver);
	}

	/**
	 * For disposing the browser after the test has finished.
	 */
	void quit() {
		driver.quit();		
	}

	/**
	 * Only for internal usage in HtmlElement
	 */
	<T extends PageObject> T create(Class<T> clazz, ElementResolver elementResolver) {
		return PageObjectFactory.createInternal(clazz, new HtmlElement(elementResolver));
	}

	/**
	 * Only for internal usage in HtmlElement
	 */
	<T extends PageObject> List<T> find(Class<T> clazz, ElementResolver elementResolver) {
		List<T> result = new ArrayList<T>();
		List<HtmlElement> elements = findElements(elementResolver);
		for (HtmlElement element : elements) {
			result.add(PageObjectFactory.createInternal(clazz, element));
		}
		return result;
	}
	
	/**
	 * Only for internal usage, will fail if there is not at least one element.
	 */
	List<HtmlElement> findElements(final ElementResolver elementResolver) {
		List<WebElement> elements = elementResolver.resolve();
		if (elements.isEmpty()) {
			fail("No HtmlElement found with " + elementResolver);
		}
		List<HtmlElement> result = new ArrayList<HtmlElement>();
		for (WebElement elem : elements) {
			result.add(new HtmlElement(elem));
		}
		return result;
	}

	/**
	 * Only for internal usage in ElementResolver
	 */
	List<WebElement> findElementsInternal(final By by) {
		return driver.findElements(by);
	}


}
