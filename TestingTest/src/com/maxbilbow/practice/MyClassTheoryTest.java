package com.maxbilbow.practice;

import static click.rmx.tests.Tests.Ex7;
import static click.rmx.tests.Tests.Ex8;
import static click.rmx.tests.Tests.colWidth;
import static click.rmx.tests.Tests.note;
import static click.rmx.tests.Tests.setColWidth;
import static click.rmx.tests.Tests.success;
import static click.rmx.tests.Tests.todo;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assume;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.rules.ExpectedException;
import org.junit.rules.Timeout;
import org.junit.runner.RunWith;

@RunWith(Theories.class)
public class MyClassTheoryTest {

	@DataPoints
	public static int[] data() {
		return new int[] {
				1, 5, 10, 15, 20, 50, -4
		};
	}
	
	MyClass object;
	
//	@Rule
//	public Timeout timeout = new Timeout(20);

	@Before
	public void setUp() throws Exception {
		object = new MyClass();
		object.setName("Fred");
	}

	@After
	public void tearDown() throws Exception {
		object = null;
	}
	
	@Theory
	public void SetValuesAlwaysResultsInPositive(int value) {
		MyClass object = new MyClass();
		object.setValue(value);
		
		Assume.assumeTrue(value > 0);
		
		assertTrue(object.getValue() > 0);
	}
	


}
