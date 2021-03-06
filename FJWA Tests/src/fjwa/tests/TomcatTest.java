package fjwa.tests;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static practice.db.WebInterface.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import practice.db.FJWAException;
import practice.db.IWebInterface;
import practice.db.TableData;
import practice.db.Tomcat;
import practice.db.WebInterface;

import static fjwa.tests.Tests.*;
/**
 * 
 */

/**
 * @author bilbowm
 *
 */
public class TomcatTest {

	private static final boolean deleteOnFinish = true;
	IWebInterface service;
	/**
	 * @throws ClassNotFoundException 
	 * @throws FJWAException 
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void ConnectToExistingTable() throws SQLException, ClassNotFoundException, FJWAException {
		WebInterface service = new WebInterface();
		if (deleteOnFinish)
		try {
			service.createStatement().execute("DROP TABLE "+ table +";");
			System.err.println("WARNING: Table should not exist yet!");
		} catch (SQLException e) {
			System.out.println("Table does not exist. Good? => " + e.getLocalizedMessage());
			service.getTableData(table);
			service.createStatement().execute("DROP TABLE "+ table +";");
		} finally {
			assertThat(
					service.getTableData(table),is(TableWasCreated)
					);
		}
		service.closeConnection();
	}

	/**
	 * @throws FJWAException 
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws FJWAException, ClassNotFoundException, SQLException {
		WebInterface service = new WebInterface();
		
		service.printTable(table);
		
		if (deleteOnFinish)
		try {
			
			service.createStatement().execute("DROP TABLE "+ table +";"); //delete table
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		service = new Tomcat();
		if (!service.isConnected())
			service.openNewConnection();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		if (service.isConnected())
			service.closeConnection();
		service = null;
	}

	@Test
	public void ConnectionIsOpen() throws SQLException {
		assertFalse(service.getConnection().isClosed());
	}

	@Test
	public void ConnectionWasClosed() throws SQLException {
		service.closeConnection();
		assertTrue(service.getConnection().isClosed());

	}
	

	@Test
	public void GetResultSetFromExistingTable() throws FJWAException {
		if (deleteOnFinish)
			assertThat(service.getTableData(table), instanceOf(ResultSet.class));
	}
	
	
	static final TableData table = TableData.test_table;
	static final String[] cols = { "name" , "email" };
	static final String[][] rows = {
				{ "John Appleseed", "an@email.biz" + Math.rint(Math.random() * 100) }
			};
	
	@Test
	public void TestInsertSQL() throws SQLException {
		System.out.println(service.buildInsertSql(
				table.name()
				, cols, rows));	
		service.insertValues(table.name(), cols, rows);
	}
}
