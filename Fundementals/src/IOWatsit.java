import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class IOWatsit  {

	

	public String getTextFromFile(String path) {
		String result = "";
		FileReader reader = null;
		BufferedReader bReader = null;
		CapReader cReader = null;
		try {
			reader = new FileReader(path);
		 bReader = new BufferedReader(reader);
			cReader = new CapReader(bReader);
			String line;
			while ((line = cReader.readLine()) != null) {
				result +=  line;
			}
			bReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result += e.getMessage();
		} finally {
			if (reader != null) {
				try {
					
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return result;
	}

	
	public void addTextToFile(String path, String text) {
		PrintWriter writer = null;
		String current = this.getTextFromFile(path);
		try {
			writer = new PrintWriter(new FileWriter( path));
			writer.println(current);

			writer.println(text);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (writer != null)
				writer.close();
		}
	}
	
	public static void main(String[] args) {
		String path = "C:\\temp\\testFile.txt";
		IOWatsit io = new IOWatsit();
		io.addTextToFile(path, "A new line!");
		String s = io.getTextFromFile(path);
		
		System.out.println(s);
	}

}
