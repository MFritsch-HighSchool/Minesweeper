import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.world.World;


public class MineSweeperWorld extends World{
		int mines;
		int timeIncrements = 0;
		Timer t;
		int diff;
	public MineSweeperWorld(int x, int y, int mineCount, int level){
		super(new BoundedGrid(x,y));
		mines = mineCount;
		minePlacer();
		numberPlacer();
		coverWorld();
		setMessage("The Timer will begin when you click.");
		timerThing();
		diff = level;
	}
	
	private void timerThing() {
		ActionListener ticker = new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(gameOvers == false)
				step();
			}
			
		};
		t = new Timer(100,ticker); 
		
	}

	Grid g = this.getGrid();
	
	public void minePlacer(){
		Random rand = new Random();
		for(int looper = 0; looper < mines; looper++){
			this.add(getRandomEmptyLocation(), new bomb());
		}
	}
	public void step(){
		this.timeIncrements++;
		this.setMessage("Clock is ticking: "+"\n"+this.timeIncrements/10+"."+timeIncrements%10);
	}
	public void numberPlacer(){
		Grid g = getGrid();
		for(int r = 0 ;r<g.getNumRows();r++) {
			for(int c = 0; c< g.getNumCols(); c++) {
				int count = 0;
				Location loc = new Location (r,c);
				ArrayList list = g.getNeighbors(loc);
				for(int index = 0; index < list.size() ; index++){
					if(list.get(index) instanceof bomb) {
						count++;
					}
				}
				if(count != 0)
					if(!(g.get(loc) instanceof bomb))
					add(loc, count);
				
			}
		}
	}
	public void coverWorld(){
		Grid g = getGrid();
		for(int r = 0; r < g.getNumRows(); r++){
			for(int c = 0; c < g.getNumCols(); c++){
				Location loc = new Location(r,c);
				add(loc, new Tile(g.get(loc)));
			}
		}
	}
	
	boolean gameOvers = false;
	boolean rClick = false;
	boolean startedYet = false;
	
	@Override
	public boolean locationClicked(Location loc){
		if(!startedYet){
			startedYet = true;
			t.start();
		}
		Grid g = getGrid();
		if(gameOvers == true){
			try {
			    Thread.sleep(1000);
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
			this.setMessage("Game over yo.");
			//highScore();
			return true;
		}
		else if(gameOvers == false){
			if(this.getGrid().get(loc) == null){
				return false;
			}
			if(rClick == true){
				rightClick(loc);
			}
			if(rClick == false){
				leftClick(loc);
			}
		}
		gameOver(loc);
		return true;
	}
	
	
	private void uncoverMines() {
		Grid g = getGrid();
		for(int r = 0; r < g.getNumRows(); r++){
			for(int c = 0; c < g.getNumCols(); c++){
				Location loc = new Location(r,c);
				Object o =  g.get(loc);
				if(o instanceof Tile){
					Tile y = (Tile)o;
					Object h = y.get();
					if(h instanceof bomb)
					add(loc, new bomb());
				}
			}
		}
	}

	@Override	
	public boolean keyPressed(String s, Location loc){
		if(s.equals("SPACE") && rClick == false){
			System.out.println("Flag placement engaged.");
			rClick = true;
		}
		else if(s.equals("SPACE") && rClick == true){
			rClick = false;
			System.out.println("Flag placement disengaged.");
		}
		return true;
	}
	
	
	public void leftClick(Location loc) {
		Grid g = getGrid();
		if(g.get(loc) instanceof flag || !(g.get(loc) instanceof Tile)){
			return;
		}
		else if(g.get(loc) instanceof Tile){
			Tile t = (Tile) g.get(loc);
			Object thing = t.get();
			if(thing != null)
				add(loc,thing);
			else{
				remove(loc);
				List<Location> near= g.getOccupiedAdjacentLocations(loc);
				for(Location place: near){
					leftClick(place);
				}
			}
		}
	}

	public void rightClick(Location loc) {
		if(g.get(loc) instanceof flag){
			Tile f = (Tile) g.get(loc);
			Object thing = f.get();
			remove(loc);
			add(loc, new Tile(thing));
			
		}
		else if(g.get(loc) instanceof Tile){
			Tile f = (Tile) g.get(loc);
			Object thing = f.get();
			remove(loc);
			add(loc, new flag(thing));
		}
			
	}

	public void gameOver(Location loc){
		Grid g = this.getGrid();
		int tiles = 0;
		int flags = 0;
		for(int r = 0; r < g.getNumRows(); r++){
			for(int c = 0; c < g.getNumCols(); c++){
				Location lel = new Location(r,c);
				Object o =  g.get(lel);
				if(o instanceof Tile){
					tiles++;
				}
				if(o instanceof bomb){
					gameOvers = true;
					System.out.println("Game over, you lose!");
					uncoverMines();
					return;
				}
				if(o instanceof flag){
					flags++;
				}
			}
		}
		if(tiles == mines && tiles == flags){
			System.out.println("You won!!");
			gameOvers = true;
			highScore();
		}
	}

	private void highScore() {
		String nameWinner = JOptionPane.showInputDialog("Enter your name for the High Score board!");
		String recTimeS = this.timeIncrements/10+"."+timeIncrements%10;
		double recTime = Double.parseDouble(recTimeS);
		HighScoreIn p = new HighScoreIn();
		p.printHighScores(nameWinner, recTime, diff);
	}
}
