package app;

import java.io.Serializable;

public class File implements Serializable {

	private static final long serialVersionUID = 1L;
	private String fileName;
	private String p1Name;
	private String p2Name;
	
	private int p1Wins;
	private int p1Draws;
	
	private int p2Wins;
	private int p2Draws;
	
	public File(String fileName, String p1Name, String p2Name, int p1Wins, int p1Draws, int p2Wins, int p2Draws) {
		this.setFileName(fileName);
		this.p1Name = p1Name;
		this.p2Name = p2Name;
		
		this.p1Wins = p1Wins;
		this.p1Draws = p1Draws;
		
		this.p2Wins = p2Draws;
		this.p2Wins = p2Wins;	
	}
	
	public String getP1Name() {
		return p1Name;
	}

	public String getP2Name() {
		return p2Name;
	}
	

	public int getP1Wins() {
		return p1Wins;
	}
	public int getP1Draws() {
		return p1Draws;
	}


	public int getP2Wins() {
		return p2Wins;
	}


	public int getP2Draws() {
		return p2Draws;
	}


	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
