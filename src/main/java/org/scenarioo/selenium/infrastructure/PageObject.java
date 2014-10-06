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

import java.util.List;

import org.openqa.selenium.By;
import org.scenarioo.selenium.infrastructure.components.PageComponent;

/**
 * Base class for all page objects. A page objects abstracts the funtionality provided by one part of your web application under test (might be whole page or just some part, some UI elements of it).
 * 
 * A page obkject always relates to a context in the DOM tree inside which this functionality lives to access it. This is the 'element' which is the topmost element for this page object.
 * For root page objects the elemnt can simply be initialized to the body element by using the default constructor.
 * 
 * See PageObject pattern for more information about this.
 */
public class PageObject {
	
	protected final HtmlElement element;

	/**
	 * Create page object in context of an HtmlElement. Should never be used directly, use factory create methods instead e.g. on PageObject itself (for sub page objects) or directly on WebTest.
	 * @param element the context element for the page object
	 */
	public PageObject(HtmlElement element) {
		this.element = element;
	}
	
	public Browser getBrowser() {
		return BrowserResource.getBrowser();
	}
	
	/**
	 * For creating components inside the scope of this component.
	 * 
	 * clazz is only needed because of type erasure in Java, in C# this might be done more elegantly.
	 */
	protected <T extends PageObject> T create(Class<T> clazz, final By byWithinElement) {
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

}
