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

import org.junit.ClassRule;
import org.junit.Rule;
import org.scenarioo.selenium.infrastructure.scenarioo.ScenarioDocuWritingRule;
import org.scenarioo.selenium.infrastructure.scenarioo.UseCaseDocuWritingRule;

/**
 * Base class for all web tests using selenium and scenarioo together for testing and documenting your software.
 * 
 * Provides most basic functionality needed in any web test. Use page objects and components for accessing the content of the web pages.
 */
public class WebTest {
	
	@Rule
	public BrowserResource browserResource = new BrowserResource();
	
	/**
	 * Rule to write the use case information for each test class
	 */
	@ClassRule
	public static UseCaseDocuWritingRule useCaseWritingRule = new UseCaseDocuWritingRule();
	
	/**
	 * Rule to write the scenario information for each test
	 */
	@Rule
	public ScenarioDocuWritingRule scenarioWritingRule = new ScenarioDocuWritingRule();
	
	/**
	 * Direct access to current browser.
	 */
	public Browser getBrowser() {
		return BrowserResource.getBrowser();
	}
	
	/**
	 * Create root page objects, for other page objects use create and find methods inside the parent page objects themselves.
	 */
	public <T extends PageObject> T create(Class<T> clazz) {
		return PageObjectFactory.create(clazz);
	}

	
}
