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

import org.junit.rules.ExternalResource;

/**
 * This jUnit rule used on each test class will ensure to always initialize and close a selenium web driver 
 * properly for each test scenario beeing run.
 * 
 * See usage in WebTest class.
 */
public class BrowserResource extends ExternalResource {
	
	private final static ThreadLocal<Browser> currentThreadsBrowser = new ThreadLocal<Browser>();	
	
	/**
	 * Get current active browser of current test thread.
	 */
	public static Browser getBrowser() {
		return currentThreadsBrowser.get();		
	}

	@Override
    protected void before() throws Throwable {
		// Simply init a Browser object for current thread, such that it is simply accessible during run of current thread for all infrastructure classes.
		Browser browser = Browser.createFirefoxDriver();
		currentThreadsBrowser.set(browser);
	};

    @Override
    protected void after() {
    	Browser browser = currentThreadsBrowser.get();
    	if (browser != null) {
    		browser.quit();
    		currentThreadsBrowser.set(null);
    	}
    };

}
