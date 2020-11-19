package app;

public interface Storage {
	File readFile(String filename);
	void printToFile(String filename, File file);
}