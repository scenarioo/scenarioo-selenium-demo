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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.scenarioo.selenium.infrastructure.HtmlElement;
import org.scenarioo.selenium.infrastructure.PageObject;

/**
 * Base class for page components.
 */
public abstract class PageComponent extends PageObject {

	public PageComponent(HtmlElement element) {
		super(element);
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
		assertTrue("Element expected to exist: " + element, element.exists());
	}

	public void assertNotExists() {
		assertFalse("Element expected not to exist: " + element, element.exists());
	}

	public void waitUntilElementDoesNotExist() {
		getBrowser().waitUntil(browser -> !element.exists());
	}
}

