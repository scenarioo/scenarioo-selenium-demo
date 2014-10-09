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

package org.scenarioo.selenium.infrastructure.components;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.openqa.selenium.By;
import org.scenarioo.selenium.infrastructure.Browser;
import org.scenarioo.selenium.infrastructure.BrowserResource;
import org.scenarioo.selenium.infrastructure.HtmlElement;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Most basic page component, other page components will extend from this base class.
 * 
 * It provides a scope (givent through the components most outer html element) to resolve subcomponents relative to for
 * composite components.
 */
public abstract class PageComponent {

	protected final HtmlElement element;

	public PageComponent(HtmlElement element) {
		super();
		this.element = element;
	}

	/**
	 * Just for convenient access to the browser.
	 */
	protected Browser getBrowser() {
		return BrowserResource.getBrowser();
	}

	/**
	 * Having asserts directly on components helps to have meaningfull error messages when an assert fails and is
	 * more convenient to write tests.
	 */
	public void assertIsDisplayed() {
		assertTrue("Element expected to be displayed: " + element, element.isDisplayed());
	}

	public void assertIsNotDisplayed() {
		assertFalse("Element expected NOT to be displayed: " + element, element.isDisplayed());
	}

	public void assertExists() {
		// Having asserts directly on components helps to have meaningfull error messages when an assert fails:
		assertTrue("Element expected to exist: " + element, element.exists());
	}

	public void assertNotExists() {
		assertFalse("Element expected not to exist: " + element, element.exists());
	}

	/**
	 * For creating components inside the scope of this component.
	 * 
	 * clazz is only needed because of type erasure in Java, in C# this might be done more elegantly.
	 */
	protected <T extends PageComponent> T create(Class<T> clazz, final By byWithinElement) {
		return element.create(clazz, byWithinElement);
	}

	/**
	 * For finding elements inside the scope of this component.
	 * 
	 * Only works if the elements of the searched components are already present.
	 */
	protected <T extends PageComponent> List<T> find(Class<T> clazz, final By byWithinElement) {
		return element.find(clazz, byWithinElement);
	}

	public String[] getCssClasses() {
		return element.getCssClasses();
	}

	public void assertCssClass(String expected) {
		assertTrue(
				"Element expected to contain css class '" + expected + "', but has: "
						+ ArrayUtils.toString(getCssClasses()),
				ArrayUtils.contains(getCssClasses(), expected));
	}

}

