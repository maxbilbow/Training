package com.maxbilbow.practice;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	MyClassTest.class, 
	ParameterizedTests.class,
	MyClassTheoryTest.class,
	MyClassRulesTest.class
})
public class AllTests {

}
