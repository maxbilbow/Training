import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class CapReader extends BufferedReader {

	@Override
	public String readLine() throws IOException {
		// TODO Auto-generated method stub
		String line = super.readLine();
		if (line != null) {
			return line.toUpperCase();
		}
		return null;
	}

	public CapReader(Reader in) {
		super(in);
		
	}

	
}
