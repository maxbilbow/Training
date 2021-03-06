

package click.rmx;

import java.lang.reflect.Method;

public class Tests {
	public static int colWidth = 40;
	public static final long 
	ONE_BILLION = 1000000000,
	Ex9 = ONE_BILLION,
	Ex8 = 100000000,
	Ex7 = 10000000,
	Ex6 = 1000000,
	Ex5 = 100000,
	Ex4 = 10000,
	Ex3 = 1000,
	Ex2 = 100
	;
	public static void setColWidth(Class<?> aClass) {
		Method[] methods = aClass.getMethods();
		colWidth = 0;
		for (Method method : methods) {
			int length = aClass.getSimpleName().length() + 4 + method.getName().length();
			if (length > colWidth)
				colWidth = length;
		}
	}

	public static void todo() {
		_note(null);
	}

	public static void note() {
		_note("testing");
	}

	public static void note(String s) {
		_note(s);
	}

	private static void _note(String s) {
		int depth = 3;
		if (s == null) {
			s = "not yet implemented";
		}
		StackTraceElement trace = Thread.currentThread().getStackTrace()[depth];
		String file = trace.getFileName();
		if (file.endsWith(".java"))
			file = file.substring(0, file.length() - 5);
		String method = trace.getMethodName();
		String log = file + "::" + method + "() ";
		if (log.length() < colWidth) {
			int diff = colWidth - log.length();
			for (int i = 0; i<=diff; ++i)
				log += ' ';
		}
		log += "=> " + s;			
		System.out.println(log);
	}

	public static void success() {
		_note("Success!");
	}
}
