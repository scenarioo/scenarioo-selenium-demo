package org.scenarioo.selenium.infrastructure.scenarioo;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.scenarioo.api.ScenarioDocuWriter;
import org.scenarioo.model.docu.entities.Step;
import org.scenarioo.model.docu.entities.StepDescription;
import org.scenarioo.model.docu.entities.StepHtml;

public class WebDriverListener implements WebDriverEventListener {
	
	private ScenariooContext context = ScenariooContext.get();
	
	private ScenarioDocuWriter writer = new ScenarioDocuWriter(
			ScenariooProperties.ROOT_DIRECTORY, ScenariooProperties.BRANCH,
			ScenariooProperties.BUILD_NAME);

	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {

	}

	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		writeStep(driver);
	}

	private void writeStep(WebDriver driver) {
		Step step = new Step();
		StepHtml stepHtml = new StepHtml(driver.getPageSource());
		step.setHtml(stepHtml);
		StepDescription stepDescription = new StepDescription();
		stepDescription.setTitle(driver.getTitle());
		byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		writer.saveScreenshotAsPng(context.getCurrentUseCase(), context.getCurrentScenario(), context.getNextStepIndex(), screenshot);
		step.setStepDescription(stepDescription);
		
		writer.saveStep(context.getCurrentUseCase(), context.getCurrentScenario(), step);
	}

	@Override
	public void beforeNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		writeStep(driver);

	}

	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		// TODO Auto-generated method stub
	}

}
