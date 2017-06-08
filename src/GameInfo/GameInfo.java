package GameInfo;

import java.util.ArrayList;

import Panels.GamePanel;
import Panels.ThePanel;

public class GameInfo {
	
	public static GameInfo current;
	
	private long timeEnded;
	private long timeStarted;
	private boolean started;
	
	private Square[][] grid;
	private GameType type;
	private int minesSelected;
	
	public GameInfo() {
		setType(new GameType(2));				//Default is expert.
		minesSelected = 0;
		timeEnded = 0;
		timeStarted = 0;
		setStarted(false);
		
		getNewSquares();
	}

	private void getNewSquares() {				//Reset grid.
		grid = new Square[type.getHeight()][type.getWidth()];
		for(int i=0; i<type.getHeight(); i++) {
			for(int j=0; j<type.getWidth(); j++) {
				grid[i][j] = new Square();
			}
		}
	}
	
	public void reset() {						//On reset field
		ThePanel.getTop().stopTimer();
		
		current.minesSelected = 0;
		current.timeEnded = 0;
		current.timeStarted = 0;
		current.started = false;
		
		getNewSquares();
		
		ThePanel.getTop().setBeginningNums();
		ThePanel.getTop().setSmileyDefault();
		
		ThePanel.getMid().getSquares();
		GamePanel.initialSquares();
		ThePanel.getMid().addListeners();
		ThePanel.getMid().repaint();
	}
	
	public void newGame(int x, int y) {			//On click
		getNewSquares();
		plantMines(x,y);
		getAdjacents();
		timeStarted = System.currentTimeMillis();
		ThePanel.getTop().startTimer();
	}

	private void plantMines(int x, int y) {		//Set mines
		int total = type.getHeight()*type.getWidth();
		ArrayList<Integer> array = new ArrayList<Integer>();
		for(int i=0; i<total; i++) {array.add(i);}
		array.remove(y*type.getWidth() + x);
		
		int place;
		for(int i=0; i<type.getMines(); i++) {
			place = (int) Math.round(Math.random()* (array.size()-1));
			grid[array.get(place) / type.getWidth()][array.get(place) % type.getWidth()].setMine(true);
			array.remove(place);
		}
		
	}
	
	private void getAdjacents() {				//Set all squares adjacent mines.
		for(int i=0; i<type.getHeight(); i++) {
			for(int j=0; j<type.getWidth(); j++) {
				grid[i][j].setAdjacents(getAdjacent(j,i));
			}
		}
	}
	
	private int getAdjacent(int x, int y) {		//Set adjacent mines for one square.
		int mines = 0;
		for(int i = y-1; i < y+2; i++) {
			for(int j = x-1; j < x+2; j++) {
				if(!(i<0 || j<0) && !(i>=type.getHeight() || j>=type.getWidth())) {
					if(grid[i][j].isMine()) {mines++;}
				}
			}
		}
		return mines;
	}
	
	public int getMinesSelected() {return minesSelected;}
	public void setMinesSelected(int minesSelected) {this.minesSelected = minesSelected;}
	public long getTimeEnded() {return timeEnded;}
	public void setTimeEnded() {this.timeEnded = 1000 + System.currentTimeMillis();}
	public long getTimeStarted() {return timeStarted;}
	public void setTimeStarted(long timeStarted) {this.timeStarted = timeStarted;}
	public void setStarted(boolean started) {this.started = started;}
	public boolean isStarted() {return started;}
	public Square[][] getGrid() {return grid;}
	public void setGrid(Square[][] grid) {this.grid = grid;}
	public GameType getType() {return type;}
	public void setType(GameType type) {this.type = type;}
	
}
