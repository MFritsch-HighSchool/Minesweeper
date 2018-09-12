import javax.swing.*;

import java.io.*;
import java.util.*;
public class HighScoreIn {
	public HighScoreIn(){
		
	}
	Scanner ins;
	Scanner inn;	
	File f = null;
	File r = null;
	boolean win1 = false;
	boolean win2 = false;
	boolean win3 = false;
	boolean win4 = false;
	boolean win5 = false;
	boolean win6 = false;
	public void printHighScores(String name, double time, int diff) {
		
		try{
			//ins = new Scanner(new File("HighScores/HSNames.txt"));
			if(diff == 0)
				f = new File("HighScores/HS.txt");
			else if(diff == 1)
				f = new File("HighScores/IHS1.txt");
			else if(diff == 2)
				f = new File("HighScores/AHS.txt");
			inn = new Scanner(f);
			double HS1 = inn.nextDouble();
			double HS2 = inn.nextDouble();
			double HS3 = inn.nextDouble();
			double HS4 = inn.nextDouble();
			double HS5 = inn.nextDouble();
			Highscores h = new Highscores(diff);
			if(time<HS1){
				win1 = true;
				h.startInts(time, HS1, HS2, HS3, HS4);
		}
			else if(time<HS2){
				win2 = true;
				h.startInts(HS1, time, HS2, HS3, HS4);
		}
			else if(time<HS3){
				win3 = true;
				h.startInts(HS1, HS2, time, HS3, HS4);
			}
			else if(time<HS4){
				win4 = true;
				h.startInts(HS1, HS2, HS3, time, HS5);
			}
			else if(time<HS5){
				win5 = true;
				h.startInts(HS1, HS2, HS3, HS4, time);
			}
			else{
				h.startInts(HS1, HS2, HS3, HS4, HS5);
				win6 = true;
			}
		}
		catch(IOException l){
			System.out.println(l.getMessage());
		}
		catch(java.util.NoSuchElementException e){
			System.out.println(e.getMessage());
		}
		try{
			r = new File("HighScores/HSNames");
			ins = new Scanner(r);
			String N1 = ins.next();
			String N2 = ins.next();
			String N3 = ins.next();
			String N4 = ins.next();
			String N5 = ins.next();
			System.out.println("Name is "+name);
			Highscores h = new Highscores(diff);
			//Fix filing at top of Try
			if(win1 == true)
				h.startStrings(name, N2, N3, N4, N5);
			else if(win2 == true)
				h.startStrings(N1, name, N3, N4, N5);
			else if(win3 == true)
				h.startStrings(N1, N2, name, N4, N5);
			else if(win4 == true)
				h.startStrings(N1, N2, N3, name, N5);
			else if(win5 == true)
				h.startStrings(N1, N2, N3, N4, name);
			else if(win6 == true)
				h.startStrings(N1, N2, N3, N4, N5);
		}
		catch(IOException l){
			System.out.println(l.getMessage());
		}
		catch(java.util.NoSuchElementException e){
			System.out.println(e.getMessage());
		}

	}
}
