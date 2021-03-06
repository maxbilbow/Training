package com.maxbilbow.practice;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;
import static click.rmx.tests.Tests.*;
public class MyClassRulesTest {

	MyClass object;
	
	@Rule
	public Timeout timeout = new Timeout(20);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		object = new MyClass();
		object.setName("Fred");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void NameIsFred() {
		assertEquals(object.getName(),"Fred");
	}

	@Test
	public void CountToHighNumber() {
		object.countTo(Ex7);
	}
}
