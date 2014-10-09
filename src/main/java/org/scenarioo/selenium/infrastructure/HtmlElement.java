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

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.scenarioo.selenium.infrastructure.components.PageComponent;

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

	private By by;
	private WebElement element;

	/**
	 * For internal use only.
	 * 
	 * Use {@link SeleniumWrapper#createInternal(Class, By)} or {@link SeleniumWrapper#find(Class, By)} to get your page
	 * components which internaly hold an HtmlElement
	 */
	HtmlElement(By by) {
		if (by == null) {
			throw new IllegalArgumentException("Not allowed to construct ElementWrapper with by='null'.");
		}
		this.by = by;
		this.element = null;
	}

	/**
	 * For internal use only.
	 * 
	 * Use {@link SeleniumWrapper#createInternal(Class, By)} or {@link SeleniumWrapper#find(Class, By)} to get your page
	 * components which internaly hold an HtmlElement
	 */
	HtmlElement(WebElement element) {
		if (element == null) {
			throw new IllegalArgumentException("Not allowed to construct ElementWrapper with element='null'.");
		}
		this.by = null;
		this.element = element;
	}


	/**
	 * Get the text of the web element
	 */
	public String getText() {
		return callOperation(new ElementOperation<String>() {
			@Override
			public String call(WebElement elem) {
				return element.getText();
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
				return element.getAttribute("value");
			}
		});
	}

	public boolean exists() {
		if (by != null) {
			// clear cash in this case to force resolve again.
			element = null;
		}
		return callOperation(new ElementOperation<Boolean>() {
			@Override
			public Boolean call(WebElement elem) {
				return element != null;
			}
		});
	}

	public boolean isSelected() {
		return callOperation(new ElementOperation<Boolean>() {
			@Override
			public Boolean call(WebElement elem) {
				return element.isSelected();
			}
		});
	}

	public boolean isDisplayed() {
		return callOperation(new ElementOperation<Boolean>() {
			@Override
			public Boolean call(WebElement elem) {
				// null safe: we consider a non existing element as simply not displayed, 
				// instead of failing the test here.
				return element != null && element.isDisplayed();
			}
		});
	}

	public boolean isEnabled(final By by) {
		return callOperation(new ElementOperation<Boolean>() {
			@Override
			public Boolean call(WebElement elem) {
				return element.isEnabled();
			}
		});
	}

	public String[] getCssClasses() {
		return callOperation(new ElementOperation<String[]>() {
			@Override
			public String[] call(WebElement elem) {
				return elem.getAttribute("class").split(" ");
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

	/**
	 * For resolving relative By inside this element
	 */
	public <T extends PageObject> T create(Class<T> clazz, final By byWithinElement) {	
		if (by != null) {
			return getBrowser().create(clazz, new ByChained(by, byWithinElement));
		}
		else {
			// TODO #356: this should be done lazyly, in case that the sub element is not yet present now (=when calling create)
			WebElement childElement = element.findElement(byWithinElement);
			if (childElement == null) {
				throw new RuntimeException("Element not found for by= " + byWithinElement + " inside element ="
						+ element);
			}
			return PageObjectFactory.createInternal(clazz, new HtmlElement(childElement));
		}
	}

	/**
	 * For resolving relative By inside this element
	 */
	public <T extends PageObject> List<T> find(Class<T> clazz, final By byWithinElement) {
		if (by != null) {
			return getBrowser().find(clazz, new ByChained(by, byWithinElement));
		}
		else {
			List<WebElement> elements = element.findElements(byWithinElement);
			List<T> result = new ArrayList<T>();
			for (WebElement elem : elements) {
				result.add(PageObjectFactory.createInternal(clazz, new HtmlElement(elem)));
			}
			return result;
		}
	}

	/**
	 * Get the browser for convenient access inside html element.
	 */
	protected Browser getBrowser() {
		return BrowserResource.getBrowser();
	}


	/**
	 * Perform web element operation with fallback in case of cached element is stale.
	 * 
	 * Template method for all other methods implemented in this class that somehow operate on the web element.
	 */
	private <T> T callOperation(ElementOperation<T> operation) {
		if (element != null && by != null) {
			// Try on cached element with fallback
			try {
				return operation.call(element);
			} catch (StaleElementReferenceException ex) {
				// Retry once with refreshed element
				element = null;
				return operation.call(getElement());
			}
		}
		else {
			return operation.call(getElement());
		}
	}

	private WebElement getElement() {
		if (element != null) {
			return element;
		}
		else {
			element = getBrowser().findElementInternal(by);
			return element;
		}
	}

	@Override
	public String toString() {
		if (by != null) {
			return by.toString();
		} else {
			String result = element.getTagName();
			String id = element.getCssValue("id");
			if (StringUtils.isNotBlank(id)) {
				result += "#" + id;
			}
			String cssClasses = getElement().getAttribute("class");
			if (StringUtils.isNotBlank(cssClasses)) {
				result += " " + cssClasses;
			}
			return result;
		}
	}

}
