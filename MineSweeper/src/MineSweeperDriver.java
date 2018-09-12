import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


public class MineSweeperDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int difficulty = 0;
		int x = 0;
		int y = 0;
		int mineCount = 0;
		List<String> optionList = new ArrayList<String>();
		optionList.add("Beginner");
		optionList.add("Intermediate");
		optionList.add("Advanced");
		Object[] options = optionList.toArray();
		Object value = JOptionPane.showInputDialog(null, "How hard would you like your game?", "input box", 0, null, options, 0);
		if(value.equals("Beginner")){
			x = 7;
			y = 7;
			mineCount = 9;
			difficulty = 0;
		}
		else if(value.equals("Intermediate")){
			x = 9;
			y = 9;
			mineCount = 17;
			difficulty = 1;
		}
		else if(value.equals("Advanced")){
			x = 19;
			y = 19;
			mineCount = 75;
			difficulty = 2;
		}
		new MineSweeperWorld(x,y,mineCount, difficulty).show();
	}

}
