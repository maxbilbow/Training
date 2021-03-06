package java8;

import java.util.*;
import java.io.File;
import java.nio.file.Files;

public class JavaFileFilter {



	private static Collection<File> closures(String url) throws UnsupportedOperationException {
		final File dir = new File(url);
		File[] files = dir.listFiles(new FileFilter() {

			@Override
			public boolean accept(File dir, String name) {
				System.out.println(name);
				return name.endsWith(".java");
			}

		});
		System.out.println(files.length);
		return makeList(files);
	}

	private static Collection<File> lambderFiles(String url) {
		final File dir = new File(url);
		FileFilter fileFilter = (File file, String name) -> name.endsWith(".java");
		return makeList(
				dir.listFiles(fileFilter)
				);
	}

	private static <T> Collection<T> makeList(T[] array){
		Collection<T> list = new ArrayList<T>();
		for (T t : array) {
			System.out.println("Adding: " + t);
			list.add(t);
		}
		return list;
	}

	public static void main(String[] args) {
		String dir = "C:\\temp";
		Collection<File> javaFiles = null;
		try {
			javaFiles = closures(dir);
			System.out.println("Without Lambda: " + javaFiles.size());
			javaFiles.forEach(System.out::println);
		} catch (UnsupportedOperationException e) {
			System.err.println(e.toString());
//			e.printStackTrace();
		}
		
		Collection<File> lambdaFiles = lambderFiles(dir);
		System.out.println("   WITH Lambda: " + lambdaFiles.size());
		lambdaFiles.forEach(System.out::println);

	}




}
