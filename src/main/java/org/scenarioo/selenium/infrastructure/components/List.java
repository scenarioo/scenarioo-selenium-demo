package org.scenarioo.selenium.infrastructure.components;

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
		return find(itemClass, By.tagName("li"));
	}
}
