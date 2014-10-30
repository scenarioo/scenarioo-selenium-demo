package org.scenarioo.selenium.infrastructure.scenarioo;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.IOUtils;

public class ScenariooContext {
	
	static {
		Thread shutdownThread = new Thread(new Runnable() {			
			@Override
			public void run() {
				triggerScenariooBuildImportOnShutdown();
			}
		});
		Runtime.getRuntime().addShutdownHook(shutdownThread);
	}

	private final static ThreadLocal<ScenariooContext> CONTEXT = new ThreadLocal<ScenariooContext>();

	private String currentUseCase;
	private String currentScenario;
	private int currentStep;
	
	private ScenariooContext() {
		// force use of get() method
	}
	
	private static void triggerScenariooBuildImportOnShutdown() {
		try {
			URL url = new URL("http://localhost:8080/scenarioo/rest/builds/updateAndImport");
			URLConnection conn = url.openConnection();
			InputStream in = conn.getInputStream();
			IOUtils.toString(in);					
		}
		catch (Throwable exception) {
			System.out.println("WARN: could not trigger build import in scenarioo (scenarioo nor running?)");
		}
	}

	public static ScenariooContext get() {
		if(CONTEXT.get() == null) {
			CONTEXT.set(new ScenariooContext());
		}
		return CONTEXT.get();
	}

	public static void reset() {
		CONTEXT.set(new ScenariooContext());
	}
	
	public String getCurrentUseCase() {
		return currentUseCase;
	}

	public void setCurrentUseCase(String currentUseCase) {
		this.currentUseCase = currentUseCase;
	}

	public String getCurrentScenario() {
		return currentScenario;
	}

	public void setCurrentScenario(String currentScenario) {
		this.currentScenario = currentScenario;
	}
	
	public synchronized int getNextStepIndex() {
		return currentStep++;
	}	
	
}
