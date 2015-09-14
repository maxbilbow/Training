import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Something(defaultNumber = 1, defaultThing = "doAThing")
public class FileMeddler {

	
	public void doAThing() {
		// TODO Auto-generated method stub
				Path path = Paths.get("C:\\temp\\testFile.txt");
				
				try {
					Files.createFile(path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
//				try {
//					Files.deleteIfExists(path);
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
				
				try {
					Files.move(path, Paths.get("C:\\temp\\test.txt"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	public static void main(String[] args) {
		FileMeddler fm = new FileMeddler();
		Something s = FileMeddler.class.getAnnotation(Something.class);
		try {
			Method m = fm.getClass().getMethod(s.defaultThing());
			m.invoke(fm);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
