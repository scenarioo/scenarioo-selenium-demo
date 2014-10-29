package org.scenarioo.selenium.infrastructure.db;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * A simple JUnit resource to setup a dataset before each webtest (clean the database, set initial data).
 */
public class DbDatasetResource implements TestRule {
	
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                before(description);
                try {
                    base.evaluate();
                } finally {
                    after(description);
                }
            }
        };
    }
	
    protected void before(Description description) throws Throwable {
    	setupDbDataset(description);
    };

    protected void after(Description description) {
    	// currently does nothing, but could be helpful for afterwards cleanup if wanted.
    };

	
	private void setupDbDataset(Description testDescription) throws Exception {
		
		// initialize your database connection here
		Class.forName("com.mysql.jdbc.Driver");
        Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost/mytinytodo", "root", "");
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

        // Insert the dataset in database.
        DatasetDefinition datasetDef = getDatasetDefinitionForMethodUnderTest(testDescription);
        IDataSet dataSet = getDataset(datasetDef);
        try
        {
            DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        }
        finally
        {
            connection.close();
        }				
	}

	private DatasetDefinition getDatasetDefinitionForMethodUnderTest(
			Description testDescription) {
		Dataset dataset = testDescription.getAnnotation(Dataset.class);
		if (dataset == null || dataset.value() == null) {
			return DatasetDefinition.DEFAULT;			
		}
		else {
			return dataset.value();
		}		
	}

	private IDataSet getDataset(DatasetDefinition datasetDef) throws DataSetException, FileNotFoundException {		
		return new FlatXmlDataSetBuilder().build(getClass().getResourceAsStream("/datasets/" + datasetDef.getFilename()));		
	}	

}
