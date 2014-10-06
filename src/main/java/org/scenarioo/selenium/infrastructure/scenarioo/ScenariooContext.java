package org.scenarioo.selenium.infrastructure.scenarioo;

public class ScenariooContext {

	private final static ThreadLocal<ScenariooContext> CONTEXT = new ThreadLocal<ScenariooContext>();

	private String currentUseCase;
	private String currentScenario;
	private int currentStep;
	
	private ScenariooContext() {
		// force use of get() method
	}

	public static ScenariooContext get() {
		if(CONTEXT.get() == null) {
			CONTEXT.set(new ScenariooContext());
		}
		return CONTEXT.get();
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
