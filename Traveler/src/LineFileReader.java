import java.io.*;

public class LineFileReader {
	
	private String filename;
	private BufferedReader reader;
	
	public LineFileReader(String filename) {
		this.filename = filename;
	}
	
	public boolean open() {
		try {
			reader = new BufferedReader(new FileReader(filename));
			return true;
		} catch (FileNotFoundException e) {
			System.err.println("File \"" + filename + "\" not found");
		} catch (IOException e) {
			System.err.println("Error while opening file \"" + filename + "\"");
		}
		return false;
	}
	
	public String readLine() {
		String line = null;
		if (reader != null) {
			try {
				line = reader.readLine();
			} catch (IOException e) {
				System.err.println("Error while reading file \"" + filename + "\"");
			}
		}
		return line;
	}
	
	public boolean close() {
		if (reader != null) {
			try {
				reader.close();
				return true;
			} catch (IOException e) {
				System.err.println("Error while closing file \"" + filename + "\"");
			}
		}
		return false;
	}
}	
	
		
