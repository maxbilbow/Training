package com.maxbilbow.practice;
import static click.rmx.tests.Tests.*;
import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


import static org.hamcrest.CoreMatchers.*;
//import static org.junit.matchers.JUnitMatchers.*;
/**
 * 
 */

/**
 * @author bilbowm
 *
 */
public class MyClassTest {
	MyClass myObject;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		setColWidth(MyClassTest.class);
		note("colWidth set to: " + colWidth);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		todo();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		myObject = new MyClass();
		note("myObject was initialized");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		myObject = null;
		note("myObject was set to null");
	}

	@Test
	@Ignore
	public void test() {
		todo();
		fail("Not yet implemented");
	}
	
	@Test
	public void ValueShouldBeWhatWasEnteredInSetValue() {
		myObject.setValue(1);
		assertEquals(1, myObject.getValue());
		note("Value set to 1");
	}
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test(expected=NullPointerException.class)
	public void ThrowExceptionWhenNameSetToNull() {
		myObject.setName(null);
		note(myObject.getName());
	}
	
	@Test
	public void ThrowExceptionWithRule() {
		thrown.expect(NullPointerException.class);
		thrown.expectMessage(containsString("NULL"));
		myObject.setName(null);
		note(myObject.getName());
	}
	
	@Test(timeout=100)
	public void CountTo100Million() {
		note("testing");
		myObject.countTo(100000000);
		success();
	}
	
	@Test(timeout=100)
	public void CountTo1Billion() {
		note("testing");
		myObject.countTo(Ex8);
		success();
		
	}
	
	@Test
	public void NewObjectsAreNotSame(){
		assertNotSame(myObject, new MyClass());
	}
	
	@Test
	public void NewObjectsAreEqual() {
		assertEquals(myObject, new MyClass());
	}
	
	@Test
	public void ObjectWithDifferentValuesNotEqual() {
		myObject.setName("Fred");
		assertNotEquals(myObject,new MyClass());
	}
	
	@Ignore
	@Test
	public void TestFail() {
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void TestPass() {
		
	}
		
	@Test
	public void TestThatNameIsFred() {
		myObject.setName("Fred");
		assertThat(myObject.getName(), is("Fred"));
		assertThat(myObject.getValue(), allOf(is(0),instanceOf(Integer.class)));
	}
	
	
}
