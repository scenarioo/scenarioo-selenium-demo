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

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.scenarioo.selenium.infrastructure.HtmlElement;

import com.google.common.base.Predicate;

public abstract class List<T extends PageComponent> extends PageComponent {
	private Class<T> itemClass;
	
	public List(HtmlElement element, Class<T> itemClass) {
		super(element);
		this.itemClass = itemClass;
	}
	
	public void assertItemExists(Predicate<T> selector) {
		try {
			find(selector);
		} catch (NoSuchElementException e) {
			Assert.fail("List item with given selector does not exist.");
		}
	}
	
	public void assertIsEmpty() {
		assertElementDoesNotExist(getItemSelector());
	}
	
	public T find(Predicate<T> selector) {
		for (T item : findAllItems()) {
			if (selector.apply(item)) {
				return item;
			}
		}
		throw new NoSuchElementException("No list item found matching the given selector");
	}
	
	public T findByIndex(int index) {
		return findAllItems().get(index);
	}

	private java.util.List<T> findAllItems() {
		return find(itemClass, getItemSelector());
	}

	/**
	 * Override this method to implement components representing lists of custom elements (other than UL, OL lists)
	 * 
	 * @return A By selector for the list items, relative to the List element
	 */
	protected By getItemSelector() {
		return By.tagName("li");
	}
}
