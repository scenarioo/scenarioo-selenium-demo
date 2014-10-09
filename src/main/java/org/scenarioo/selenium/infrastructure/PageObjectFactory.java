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

import java.lang.reflect.Constructor;

import org.openqa.selenium.By;

/**
 * Factory to create any page object.
 * Should not be used directly in your test code.
 * 
 * Either use create methods on your {@link WebTest} or {@link Browser} or on {@link PageObject} instead to always create objects in correct context.
 */
public class PageObjectFactory {
	
	/**
	 * Create a page object as root page object directly using the html body as the POs context element.
	 * @return the created root Page Object.
	 */
	public static <T extends PageObject> T create(Class<T> clazz) {
		return createInternal(clazz, new HtmlElement(By.tagName("body")));
	}
	
	/**
	 * Create a page object in context of a DOM element with gven By.
	 * @param clazz the class to create
	 * @param by the by that points to the page object's DOM element.
	 * @return the created page object
	 */
	public static <T extends PageObject> T create(Class<T> clazz, By by) {
		return createInternal(clazz, new HtmlElement(by));
	}
	
	/**
	 * Only for internal usage.
	 */
	static <T extends PageObject> T createInternal(Class<T> clazz, HtmlElement element) {
		try {
			Constructor<T> constructor = clazz.getConstructor(HtmlElement.class);
			return constructor.newInstance(element);
		} catch (Exception e) {
			throw new RuntimeException("Could not create pagecomponent for element = " + element, e);
		}
	}

}
