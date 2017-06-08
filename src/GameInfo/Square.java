package GameInfo;

public class Square {
	
	private int adjacents;

	private boolean mine;
	
	Square() {
		adjacents = 0;
		mine = false;
	}
	
	public int getAdjacents() {return adjacents;}
	public void setAdjacents(int adjacents) {this.adjacents = adjacents;}
	public boolean isMine() {return mine;}
	public void setMine(boolean mine) {this.mine = mine;}
	
}
