package org.scenarioo.selenium.infrastructure.db.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

/**
 * A simple command line tool to export the current database state as a flat xml dataset file
 * Simply exports to a file called "temp-dataset.xml" (you can copy this file afterwards) 
 */
public class DbDatasetExtractor {
	
	public static void main(String[] args) throws Exception
    {
        // database connection
		Class.forName("com.mysql.jdbc.Driver");
        Connection jdbcConnection = DriverManager.getConnection("jdbc:mysql://localhost/mytinytodo", "root", "");
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
        
        // full database export
        IDataSet fullDataSet = connection.createDataSet();
        File file = new File("temp-dataset.xml");
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream(file));
        
        System.out.println(" DB EXPORT SUCCESS - The extracted flat dataset can be copied from file: " + file.getAbsolutePath());
        
    }

}
