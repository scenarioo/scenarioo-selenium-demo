package org.scenarioo.selenium.infrastructure.scenarioo;


import static org.scenarioo.selenium.infrastructure.scenarioo.ScenariooProperties.BRANCH;
import static org.scenarioo.selenium.infrastructure.scenarioo.ScenariooProperties.BUILD_NAME;
import static org.scenarioo.selenium.infrastructure.scenarioo.ScenariooProperties.ROOT_DIRECTORY;

import org.apache.log4j.Logger;
import org.junit.ClassRule;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.scenarioo.api.ScenarioDocuWriter;
import org.scenarioo.model.docu.entities.UseCase;

/**
 * A {@link TestRule} to setup as a static {@link ClassRule} on your UI test classes to generate documentation content
 * for each running test class as a Usecase inside the Scenarioo Documentation.
 */
public class UseCaseDocuWritingRule implements TestRule {
	
	private static final Logger LOGGER = Logger.getLogger(UseCaseDocuWritingRule.class);
	
	@Override
	public Statement apply(final Statement test, final Description testClassDescription) {
		
		// Statement to write use case description as soon as test class gets executed
		return new Statement() {
			
			private final ScenarioDocuWriter docuWriter = new ScenarioDocuWriter(ROOT_DIRECTORY,
					BRANCH, BUILD_NAME);
			
			@Override
			public void evaluate() throws Throwable {
				try {
					// Save use case description
					UseCase useCase = createUseCase(testClassDescription.getTestClass());
					LOGGER.info("Generating Scenarioo Docu for UseCase " + useCase.getName() + ": "
							+ useCase.getDescription());
					docuWriter.saveUseCase(useCase);
					// Run tests
					test.evaluate();
				} finally {
					// Wait until asynch writing has finished.
					docuWriter.flush();
				}
			}
		};
	}
	
	public static UseCase createUseCase(final Class<?> testClass) {
		// Extract usecase name and description from concrete test class.
		String description = "";
		String name = createUseCaseName(testClass);
//		DocuDescription docuDescription = testClass.getAnnotation(DocuDescription.class);
//		if (docuDescription != null) {
//			description = docuDescription.description();
//		}
		// Create use case
		UseCase useCase = new UseCase();
		useCase.setName(name);
		useCase.setDescription(description);
		useCase.addDetail("Webtest Class", testClass.getName());
//		Labels labels = testClass.getAnnotation(Labels.class);
//		if (labels != null) {
//			useCase.getLabels().setLabels(new HashSet<String>(Arrays.asList(labels.value())));
//		}
		return useCase;
	}
	
	public static String createUseCaseName(final Class<?> testClass) {
//		DocuDescription description = testClass.getAnnotation(DocuDescription.class);
//		if (description != null && !StringUtils.isBlank(description.name())) {
//			return description.name();
//		} else {
//			// simply use the test class name as use case name if not set through description annotation.
//			return testClass.getSimpleName();
//		}
		return testClass.getSimpleName();
	}
	
}
