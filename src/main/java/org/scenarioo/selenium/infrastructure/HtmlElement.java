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

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.scenarioo.selenium.infrastructure.components.PageComponent;

import com.google.common.collect.Lists;

/**
 * Wrapper for a {@link By} or a {@link WebElement} to provide operations on the identified element to implement page
 * components.
 * 
 * Advantage of this wrapper is, that implementer of page components do not have to care whether the element has already
 * been resolved or it is only a By to resolve lazyly.
 * Once a By is resolved the WebElement will be cached, but in case of Stale-Exception the element will do a retry to
 * resolve the By again.
 * 
 * This element should not be used directly in webtests but only for implementing concrete page components.
 * (see {@link PageComponent} and subclasses).
 * 
 * Pay attention we do probably not want to expose the whole interface of WebElement, some methods should be used
 * with care, in this cases we should better provide some higher abstraction to the page component implementors!
 */
public final class HtmlElement {

	private ElementResolver elementResolver;
	private WebElement cachedElement = null;

	/**
	 * For internal use only.
	 * 
	 * Use {@link HtmlElement#create(Class, By)} or {@link HtmlElement#find(Class, By)} to get your page
	 * components which internally hold an HtmlElement
	 */
	HtmlElement(By by) {
		this(new ByElementResolver(getBrowser(), by));
	}

	/**
	 * For internal use only.
	 * 
	 * Use {@link HtmlElement#create(Class, By)} or {@link HtmlElement#find(Class, By)} to get your page
	 * components which internally hold an HtmlElement
	 */
	HtmlElement(WebElement element) {
		this(new PreLoadedElementResolver(element));
	}

	/**
	 * For internal use only.
	 * 
	 * Use {@link HtmlElement#create(Class, By)} or {@link HtmlElement#find(Class, By)} to get your page
	 * components which internally hold an HtmlElement
	 */
	HtmlElement(ElementResolver elementResolver) {
		if (elementResolver == null) {
			throw new IllegalArgumentException("Not allowed to construct HtmlElement with elementResolver='null'.");
		}
		this.elementResolver = elementResolver;
	}


	/**
	 * Get the text of the web element
	 */
	public String getText() {
		return callOperation(new ElementOperation<String>() {
			@Override
			public String call(WebElement elem) {
				return elem.getText();
			}
		});
	}

	/**
	 * Get the value of the web element
	 */
	public String getValue() {
		return callOperation(new ElementOperation<String>() {
			@Override
			public String call(WebElement elem) {
				return elem.getAttribute("value");
			}
		});
	}

	public boolean exists() {
		return !getBrowser().findElements(elementResolver).isEmpty();
	}

	public boolean isSelected() {
		return callOperation(new ElementOperation<Boolean>() {
			@Override
			public Boolean call(WebElement elem) {
				return elem.isSelected();
			}
		});
	}

	public boolean isDisplayed() {
		return callOperation(new ElementOperation<Boolean>() {
			@Override
			public Boolean call(WebElement elem) {
				// null safe: we consider a non existing element as simply not displayed, 
				// instead of failing the test here.
				return elem != null && elem.isDisplayed();
			}
		});
	}

	public boolean isEnabled(final By by) {
		return callOperation(new ElementOperation<Boolean>() {
			@Override
			public Boolean call(WebElement elem) {
				return elem.isEnabled();
			}
		});
	}
	
	public void assertCssClass(String expectedCssClass) {
		List<String> cssClasses = getCssClasses();
		assertTrue("Expected CSS class " + expectedCssClass + ", found " + cssClasses, cssClasses.contains(expectedCssClass));
	}

	private List<String> getCssClasses() {
		return callOperation(new ElementOperation<List<String>>() {
			@Override
			public List<String> call(WebElement elem) {
				return Lists.newArrayList(elem.getAttribute("class").split(" "));
			}
		});
	}

	public void click() {
		callOperation(new ElementOperation<Void>() {
			@Override
			public Void call(WebElement elem) {
				elem.click();
				return null;
			}
		});
	}

	public void doubleClick() {
		callOperation(new ElementOperation<Void>() {
			@Override
			public Void call(WebElement elem) {
				Actions action = getBrowser().createActions();
				action.doubleClick(elem).perform();
				return null;
			}
		});
	}

	public Select createSelect() {
		return callOperation(new ElementOperation<Select>() {
			@Override
			public Select call(WebElement elem) {
				return new Select(elem);
			}
		});
	}
	
	/**
	 * Clear a text input element
	 */
	public void clear() {
		callOperation(new ElementOperation<Void>() {
			@Override
			public Void call(WebElement elem) {
				elem.clear();
				return null;
			}
		});
	}
	
	public void sendKeys(final String text) {
		callOperation(new ElementOperation<Void>() {
			@Override
			public Void call(WebElement elem) {
				elem.sendKeys(text);
				return null;
			}
		});
	}

	/**
	 * Define a page component of concrete type for later use.
	 */
	public <T extends PageObject> T create(Class<T> clazz, final By byWithinElement) {	
		return getBrowser().create(clazz, elementResolver.childResolver(byWithinElement));
	}

	/**
	 * Find components of a concrete type in the current DOM.
	 */
	public <T extends PageObject> List<T> find(Class<T> clazz, final By byWithinElement) {
		return getBrowser().find(clazz, elementResolver.childResolver(byWithinElement));
	}
	

	/**
	 * Get the browser for convenient access inside html element.
	 */
	protected static Browser getBrowser() {
		return BrowserResource.getBrowser();
	}


	/**
	 * Perform web element operation with fallback in case of cached element is stale.
	 * 
	 * Template method for all other methods implemented in this class that somehow operate on the web element.
	 */
	private <T> T callOperation(ElementOperation<T> operation) {
		if (cachedElement != null) {
			// Try on cached element with fallback
			try {
				return operation.call(cachedElement);
			} catch (StaleElementReferenceException ex) {
				// Retry once with refreshed element
				cachedElement = null;
				return operation.call(getElement());
			}
		}
		else {
			return operation.call(getElement());
		}
	}

	private WebElement getElement() {
		if (cachedElement == null) {
			List<WebElement> elements = elementResolver.resolve();
			if (elements.size() != 1) {
				fail("Expected exactly 1 element, but found " + elements.size() + " with " + elementResolver);
			}
			cachedElement = elements.get(0);
		}
		return cachedElement;
	}

	@Override
	public String toString() {
		return elementResolver.toString();
	}

}
