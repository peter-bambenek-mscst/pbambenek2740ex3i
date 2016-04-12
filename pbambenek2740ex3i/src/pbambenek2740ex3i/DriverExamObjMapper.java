package pbambenek2740ex3i;

import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.DefaultListModel;



public class DriverExamObjMapper {

	private String fileName;
	private Scanner inputFile;
	private File file;
	
	public DriverExamObjMapper(String fileName) {
		super();
		this.fileName = fileName;
	}
	
	public boolean openInputFile() {
		boolean fileOpened = false;
		// Open the file.
	    try {
	    	File file = new File(this.fileName);
			fileOpened = file.exists();
			
			if (fileOpened) {
				this.inputFile = new Scanner(file);
			}
	    }
	    catch (IOException e) {}
		return fileOpened;
	}
	
	public void closeInputFile() {
		if (this.inputFile != null) inputFile.close();
	}
	
	public DriverExam getDriverExam() {
		DriverExam exam = null;
		
		DefaultListModel driverExamKey = new DefaultListModel();
			
		if (this.openInputFile())
		    {
				// make an input loop out of file contents
				while (this.inputFile.hasNext()) {
					String answer = inputFile.nextLine();
					driverExamKey.addElement(answer);
				}
				exam = new DriverExam(driverExamKey);
		    }
		this.closeInputFile();				
		return exam;
	}
	
	
}
