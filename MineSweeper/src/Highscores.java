import javax.swing.*;

import java.io.*;
import java.util.*;
public class Highscores {
	int diff;
	public Highscores(int difficulty){
		diff = difficulty;
	}

	public void startInts(double HS1, double HS2, double HS3, double HS4, double HS5) {
		try{
			File he = new File("HighScores/HS.txt");
			if(diff == 1)
				he = new File("HighScores/IHS1.txt");
			else if(diff == 2)
				he = new File("HighScores/AHS.txt");
			FileWriter n = new FileWriter(he);
			n.write(""+HS1);n.write("\n");
			n.write(""+HS2);n.write("\n");
			n.write(""+HS3);n.write("\n");
			n.write(""+HS4);n.write("\n");
			n.write(""+HS5);n.write("\n");
			n.close();
			//if(when I compare the integers to the time in minesweeper, mine is smaller)
			//then I want you to reset the integers, and also the strings for names!
			//done!
		}
		catch(IOException l){
			System.out.println(l.getMessage());
		}
	}
	public void startStrings(String NO1, String NO2, String NO3, String NO4, String NO5){
		try{
			File ho = new File("HighScores/HSNames.txt");
			FileWriter s = new FileWriter(ho);
			System.out.println(NO1+" Is N1");
			s.write(NO1);s.write("\n");
			s.write(NO2);s.write("\n");
			s.write(NO3);s.write("\n");
			s.write(NO4);s.write("\n");
			s.write(NO5);s.write("\n");
			s.close();
		}
		catch(IOException l){
			System.out.println(l.getMessage());
		}
	}

}
	

