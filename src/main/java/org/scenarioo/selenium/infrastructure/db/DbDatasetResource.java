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
    	// currently does nothing, but could be helpfull for afterwards cleanup if wanted.
    };

	
	private void setupDbDataset(Description testDescription) throws Exception {
		
		// initialize your database connection here
		Class.forName("com.mysql.jdbc.Driver");
        Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost/mytinytodo", "root", "");
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

        // Insert the dataset in database.
        IDataSet dataSet = getDataset(testDescription);
        try
        {
            DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
        }
        finally
        {
            connection.close();
        }				
	}

	private IDataSet getDataset(Description testDescription) throws DataSetException, FileNotFoundException {		
		return new FlatXmlDataSetBuilder().build(getClass().getResourceAsStream("/datasets/default-dataset.xml"));		
	}	

}
