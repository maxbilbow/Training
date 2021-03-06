package part6.jss;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import javafx.stage.Stage;

public class Scripter {
	String title = "Null";
	static String script = "";
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	ScriptEngineManager manager = new ScriptEngineManager();
	ScriptEngine engine = manager.getEngineByName("nashorn");
	String script() {
		
		try (Stream<String> stream = Files.lines(Paths.get("C:/Users/bilbowm/workspace/WhatsNew/src/part6/jss/jss.js"))) {
			stream//.filter(line -> line.contains("ERROR"))
			//.findFirst()
			.forEachOrdered(line -> {
				System.out.println(line);
				script += line;
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return script;// "stage.title = 'Whhaa?!'";
	}
	public void start(Object thing) {
		Bindings scope = engine.createBindings();
		scope.put("thing", thing);
		String script = script();
		try {
//			System.out.print(script);
			engine.eval(script, scope);
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Scripter s = new Scripter();
		
		s.start(s);
		
		System.out.println(s.title);
	}

}
