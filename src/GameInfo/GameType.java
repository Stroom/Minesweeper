package GameInfo;

public class GameType {
	
	private int width;
	private int height;
	private int mines;
	
	public GameType(int num) {		// 0 beginner, 1 intermediate, 2 expert
		switch(num) {
			case 0	:
				this.width = 9;
				this.height = 9;
				this.mines = 10;
				break;
			case 1	:
				this.width = 16;
				this.height = 16;
				this.mines = 40;
				break;
			case 2	:
				this.width = 30;
				this.height = 16;
				this.mines = 99;
				break;
		}
	}
	
	public GameType(int w, int h, int m) {
		if(w < 9) w = 9;
		if(h < 9) h = 9;
		
		if(w>30) w = 30;
		if(h>24) h = 24;
		
		if(m<10) m = 10;
		if((w-1) * (h-1) < m) {
			m = (w-1) * (h-1);
		}
		this.width = w;
		this.height = h;
		this.mines = m;
	}
	
	public int getWidth() {return width;}
	public void setWidth(int width) {this.width = width;}
	public int getHeight() {return height;}
	public void setHeight(int height) {this.height = height;}
	public int getMines() {return mines;}
	public void setMines(int mines) {this.mines = mines;}
	
}
