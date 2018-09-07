package linebreak.fix;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class LineBreak {
	
	String separator = System.lineSeparator();
	File file;
	List<String> fileLines = new ArrayList<>();
	
	static final String NEWLINE = "\n";
	static final String CARRIAGE = "\r\n";
	
	/**
	 * 
	 * @param filePath
	 */
	public void setFile(String filePath) {
		
		file = new File(filePath);
		if (file.exists() && file.canRead()) {
			System.out.println("File exists and can be read!");
		}
		else {
			System.out.println("File not found or can't be read!");
		}
	}
	
	/**
	 * 
	 */
	public void readLines() {
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				fileLines.add(line);
			}
		}
		catch(FileNotFoundException e) {
			System.out.println("File Not Found!");
			e.printStackTrace();
		}
		catch(IOException e) {
			System.out.println("IOException!");
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	public void printList() {
		for (int i = 0; i < fileLines.size(); i++) {
			System.out.println("line nr. " + (i + 1) + "  " + fileLines.get(i));
		}
	}
	
	/**
	 * 
	 */
	public void writeFile(String filename, String lineEnd) {
		
		if (lineEnd.equals(NEWLINE) == false && lineEnd.equals(CARRIAGE) == false) {
			throw new IllegalArgumentException("Invalid end-of-line character.");
		}

		File newFile = new File(filename);
		try {
			if (newFile.createNewFile()) {

				try (Writer writer = new BufferedWriter(
						new OutputStreamWriter(
								new FileOutputStream(filename), "utf-8"))) {
					for (String line: fileLines) {
						writer.write(line + lineEnd);
					}
				}
				catch(UnsupportedEncodingException e) {

				}
				catch(FileNotFoundException e) {

				}
				catch(IOException e) {

				}
			}
			else {
				System.out.println("File with this name already exists!");
			}
		}
		catch(IOException e) {

		}
	}
	    
	public static void main(String[] args) {
		
		LineBreak lb = new LineBreak();
		System.out.println(lb.separator);
		
		lb.setFile("test.txt");
		lb.readLines();
		lb.printList();
		
		lb.writeFile("blub.txt", CARRIAGE);

	}

}
