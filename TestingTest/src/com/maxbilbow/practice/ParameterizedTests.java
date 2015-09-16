package com.maxbilbow.practice;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import static click.rmx.tests.Tests.*;
@RunWith(Parameterized.class)
public class ParameterizedTests {

	private static MyClass object = new MyClass();
	private int input;
	private int expectedValue;
	
	@Parameters
	public static List<Object[]> data() {
		List<Object[]> list = new ArrayList<Object[]>();
		
		for (int i = 0; i < 500 ; ++i)
			list.add(new Object[] {i,i} );
		return list;
		
	}
	
	public ParameterizedTests(int input, int expectedValue) {
		this.input = input;
		this.expectedValue = expectedValue;
		
	}
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		setColWidth(ParameterizedTests.class);
	}



	
	static int count = 0;
	@Test
	public void RandomSetValueFailTest() {
		if (input < 0)
			note("Use syntax on input to specify different outcome");
		else {
			object.setValue(input);
			assertEquals(object.getValue(),expectedValue);//, "Failed after " + count + "tries");
		}
		count++;
	}
}
