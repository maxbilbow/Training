package click.rmx.tests;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

import com.maxbilbow.practice.AllTests;

public class ConsoleRunner {

	public static void main(String[] args) {
		JUnitCore junit = new JUnitCore();
		junit.addListener(new TextListener(System.out));
		junit.run(AllTests.class);

	}

}
