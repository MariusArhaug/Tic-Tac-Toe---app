package app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class UpdateFile implements Storage{	
	private final String projectUrl = "C:\\Users\\mariu\\tdt4100-v2020-master2\\git\\tdt4100-v2020-students\\ovinger\\src\\app";
	
	public UpdateFile() {
		
	}

	@Override
	public File readFile(String filename) {		
		try (FileInputStream savedFile = new FileInputStream(projectUrl + "//" + filename + ".txt")) {
			ObjectInputStream objectOut = new ObjectInputStream(savedFile);
			
			return (File) objectOut.readObject();
			
		} catch (FileNotFoundException e) {
			return (File) null;
		} catch (IOException e1) {
			return (File) null;
		} catch (ClassNotFoundException e) {
			return (File) null;
		}
		
	}

	@Override
	public void printToFile(String filename, File file) {
		
		try (FileOutputStream newFile = new FileOutputStream(projectUrl + "//" + filename + ".txt")) {
			ObjectOutputStream objectOut = new ObjectOutputStream(newFile);
			objectOut.writeObject(file);
			
			System.out.println("The object has been sucessfully written to a file");
			
						
		} catch (Exception e) {
			System.err.println("Error: file 'test.txt' could not be opened. Does it exist?");
		}	
	}

}
