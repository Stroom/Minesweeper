package Panels;

import java.awt.Dimension;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

import Borders.DarkLightBorder;
import GameInfo.GameInfo;
import GameInfo.Square;
import Graphics.MineButton;


public class GamePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private static MineButton[][] squares;
	
	int oldx;
	int oldy;
	
	ArrayList<int[]> ads;
	
	MouseListener ml;
	MouseMotionListener mml;
	
	GamePanel() {
		setLayout(null);
		setBorder(new DarkLightBorder(3));
		
		Dimension size = new Dimension(6 + GameInfo.current.getType().getWidth() * 16, 6 + GameInfo.current.getType().getHeight() * 16);
		setMinimumSize(size);
		setMaximumSize(size);
		setPreferredSize(size);
		setSize(size);
		
		ads = new ArrayList<int[]>();
		
		getSquares();
		setListeners();
		
		addListeners();
		
	}
	
	public void addListeners() {
		removeMouseListener(ml);
		removeMouseMotionListener(mml);
		
		addMouseListener(ml);
		addMouseMotionListener(mml);
	}
	
	private void setListeners() {
		
		ml = new MouseListener() {
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
			
			public void mousePressed(MouseEvent e) {
				
				if(e.getButton() == MouseEvent.BUTTON1) {ThePanel.getTop().setSmileyPressed(true);}
				if(getComponentAt(e.getPoint()) != null && getComponentAt(e.getPoint()).getName() != null 
						&& getComponentAt(e.getPoint()).getName().equals("MB")) {
					if(oldx != -1 || oldy != -1) {squares[oldy][oldx].setPressed(false);}
					
					int x = getComponentAt(e.getPoint()).getX()/16;
					int y = getComponentAt(e.getPoint()).getY()/16;
					oldx = x;
					oldy = y;
					
					if(e.getButton() == MouseEvent.BUTTON1) {
						if(e.getModifiersEx() == InputEvent.BUTTON3_DOWN_MASK + InputEvent.BUTTON1_DOWN_MASK) {
							selectAdjacents(x,y);
						}
						else {
							squares[y][x].setPressed(true);
						}
						
					}
					else if(e.getButton() == MouseEvent.BUTTON3) {
						if(e.getModifiersEx() == InputEvent.BUTTON3_DOWN_MASK + InputEvent.BUTTON1_DOWN_MASK) {
							selectAdjacents(x,y);
						}
						else {
							if(squares[y][x].isFlagged()) {squares[y][x].setFlagged(false);}
							else if(!squares[y][x].isOpen()){squares[y][x].setFlagged(true);}
							GameInfo.current.setMinesSelected(getAllFlags());
							ThePanel.getTop().setMinePanelNum(GameInfo.current.getType().getMines() - GameInfo.current.getMinesSelected());
							
							squares[y][x].setPressed(false);
						}
					}
					
					repaint();
				}
				else {
					oldx = -1;
					oldy = -1;
				}
			}
			public void mouseReleased(MouseEvent e) {
				
				if(e.getButton() == MouseEvent.BUTTON1) {ThePanel.getTop().setSmileyPressed(false);}
				if(getComponentAt(e.getPoint()) != null && getComponentAt(e.getPoint()).getName() != null 
						&& getComponentAt(e.getPoint()).getName().equals("MB")) {
					int x = getComponentAt(e.getPoint()).getX()/16;
					int y = getComponentAt(e.getPoint()).getY()/16;
					Square sq = GameInfo.current.getGrid()[y][x];
					if(e.getButton() == MouseEvent.BUTTON1) {
						if(e.getModifiersEx() == InputEvent.BUTTON3_DOWN_MASK) {
							if(squares[y][x].isOpen() && !sq.isMine() && sq.getAdjacents() == getAdjacentFlags(x,y)) {
								if(openAdjacents()) {
									checkOver();
								}
								else {
									endGame();
								}
							}
							removeAdjacents();
						}
						else if(!squares[y][x].isFlagged() && !squares[y][x].isOpen()) {
							if(!GameInfo.current.isStarted()) {
								GameInfo.current.newGame(x, y);
								GameInfo.current.setStarted(true);
							}
							squares[y][x].setOpen(true);
							if(GameInfo.current.getGrid()[y][x].getAdjacents() == 0) {opening(x,y);}
							if(GameInfo.current.getGrid()[y][x].isMine()) {
								squares[y][x].setBlown(true);
								endGame();
							}
							else {checkOver();}
						}
						
					}
					else if(e.getButton() == MouseEvent.BUTTON3) {
						if(e.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK) {
							if(squares[y][x].isOpen() && !sq.isMine() && sq.getAdjacents() == getAdjacentFlags(x,y)) {
								if(openAdjacents()) {
									checkOver();
								}
								else {
									endGame();
								}
							}
							removeAdjacents();
						}
					}
					repaint();
				}
				oldx = -1;
				oldy = -1;
			}
		};
		
		mml = new MouseMotionListener() {
			public void mouseDragged(MouseEvent e) {
				if(getComponentAt(e.getPoint()) != null && getComponentAt(e.getPoint()).getName() != null 
						&& getComponentAt(e.getPoint()).getName().equals("MB")) {
					int x = getComponentAt(e.getPoint()).getX()/16;
					int y = getComponentAt(e.getPoint()).getY()/16;
					
					if(e.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK + InputEvent.BUTTON3_DOWN_MASK) {
						removeAdjacents();
						selectAdjacents(x,y);
					}
					else if(e.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK) {
						if( oldx != x || oldy != y) {
							if(oldx != -1 || oldy != -1) {squares[oldy][oldx].setPressed(false);}
							squares[y][x].setPressed(true);
							oldx = x;
							oldy = y;
						}
					}
					repaint();
				}
				else {
					if(oldx != -1 || oldy != -1) {squares[oldy][oldx].setPressed(false);}
					removeAdjacents();
					oldx = -1;
					oldy = -1;
					repaint();
				}
			}
			public void mouseMoved(MouseEvent e) {}
		};
		
	}
	
	public void setNewSize() {
		Dimension size = new Dimension(6 + GameInfo.current.getType().getWidth() * 16, 6 + GameInfo.current.getType().getHeight() * 16);
		setMinimumSize(size);
		setMaximumSize(size);
		setPreferredSize(size);
		setSize(size);
	}
	
	public void getSquares() {
		removeAll();
		squares = new MineButton[GameInfo.current.getType().getHeight()][GameInfo.current.getType().getWidth()];
		for(int i=0; i<GameInfo.current.getType().getHeight(); i++) {
			for(int j=0; j<GameInfo.current.getType().getWidth(); j++) {
				squares[i][j] = new MineButton(j,i);
				squares[i][j].setBounds(j*16+3, i*16+3, 16, 16);
				add(squares[i][j]);
			}
		}
	}

	public static void initialSquares() {
		for(int i=0; i<GameInfo.current.getType().getHeight(); i++) {
			for(int j=0; j<GameInfo.current.getType().getWidth(); j++) {
				squares[i][j].setOpen(false);
				squares[i][j].setFlagged(false);
				squares[i][j].setPressed(false);
				squares[i][j].setBlown(false);
				squares[i][j].revalidate();
			}
		}
	}
	
	private void checkOver() {
		if(isOver()) {
			GameInfo.current.setTimeEnded();
			removeMouseListener(ml);
			removeMouseMotionListener(mml);
			ThePanel.getTop().setSmileyWon(true);
			ThePanel.getTop().stopTimer();
			ThePanel.getTop().setSecondPanelNum(Math.max(1,(int) (GameInfo.current.getTimeEnded() - GameInfo.current.getTimeStarted())/1000));
			
			flagMines();
		}
	}
	
	private boolean isOver() {
		for(int i=0; i<GameInfo.current.getType().getHeight(); i++) {
			for(int j=0; j<GameInfo.current.getType().getWidth(); j++) {
				if(!squares[i][j].isOpen() && !GameInfo.current.getGrid()[i][j].isMine()) {
					return false;
				}
			}
		}
		return true;
	}
	
	private void endGame() {
		GameInfo.current.setTimeEnded();
		removeMouseListener(ml);
		removeMouseMotionListener(mml);
		ThePanel.getTop().setSmileyLost(true);
		ThePanel.getTop().stopTimer();
		ThePanel.getTop().setSecondPanelNum(Math.max(1,(int)(GameInfo.current.getTimeEnded() - GameInfo.current.getTimeStarted())/1000));
		
		revealMines();
	}
	
	private void flagMines() {
		for(int i=0; i<GameInfo.current.getType().getHeight(); i++) {
			for(int j=0; j<GameInfo.current.getType().getWidth(); j++) {
				if(GameInfo.current.getGrid()[i][j].isMine()) {
					squares[i][j].setFlagged(true);
				}
			}
		}
	}
	
	private void revealMines() {
		for(int i=0; i<GameInfo.current.getType().getHeight(); i++) {
			for(int j=0; j<GameInfo.current.getType().getWidth(); j++) {
				if(GameInfo.current.getGrid()[i][j].isMine() && !squares[i][j].isFlagged()) {
					squares[i][j].setOpen(true);
				}
				if(!GameInfo.current.getGrid()[i][j].isMine() && squares[i][j].isFlagged()) {
					squares[i][j].setBlown(true);
				}
			}
		}
	}
	
	private void opening(int x, int y) {
		ArrayList<int[]> used = new ArrayList<int[]>();
		ArrayList<int[]> check = new ArrayList<int[]>();
		int[] adding;
		
		used.add(new int[]{y,x});
		for(int i = -1; i<2; i++) {
			for(int j = -1; j<2; j++) {
				if(x+j >= 0 && x+j < GameInfo.current.getType().getWidth() && y+i >= 0 && y+i < GameInfo.current.getType().getHeight()) {
					check.add(new int[]{y+i,x+j});
				}
			}
		}
		while(check.size() > 0) {
			if(!squares[check.get(0)[0]][check.get(0)[1]].isFlagged()) {
				squares[check.get(0)[0]][check.get(0)[1]].setOpen(true);
			}
			if(contains(used, check.get(0)) || GameInfo.current.getGrid()[check.get(0)[0]][check.get(0)[1]].getAdjacents() > 0) {
				check.remove(0);
			}
			else {
				used.add(check.get(0));
				for(int i = -1; i<2; i++) {
					for(int j = -1; j<2; j++) {
						if(check.get(0)[1]+j >= 0 && check.get(0)[1]+j < GameInfo.current.getType().getWidth() 
								&& check.get(0)[0]+i >= 0 && check.get(0)[0]+i < GameInfo.current.getType().getHeight()) {
							adding = new int[]{check.get(0)[0]+i,check.get(0)[1]+j};
							if(!contains(used, adding) && !GameInfo.current.getGrid()[adding[0]][adding[1]].isMine()) {
								check.add(adding);
							}
						}
					}
				}
				check.remove(0);
			}
		}
		
	}
	
	private boolean contains(ArrayList<int[]> used, int[] is) {
		for(int i=0; i<used.size(); i++) {
			if(used.get(i)[0] == is[0] && used.get(i)[1] == is[1]) {return true;}
		}
		return false;
	}
	
	private int getAdjacentFlags(int x, int y) {
		int n = 0;
		for(int i = -1; i<2; i++) {
			for(int j = -1; j<2; j++) {
				if(x+j >= 0 && x+j < GameInfo.current.getType().getWidth() && y+i >= 0 && y+i < GameInfo.current.getType().getHeight()) {
					if(squares[y+i][x+j].isFlagged()) {n++;}
				}
			}
		}
		return n;
	}
	
	private int getAllFlags() {
		int n = 0;
		for(int i=0; i<GameInfo.current.getType().getHeight(); i++) {
			for(int j=0; j<GameInfo.current.getType().getWidth(); j++) {
				if(squares[i][j].isFlagged()) {n++;}
			}
		}
		return n;
	}
	
	private void selectAdjacents(int x, int y) {
		for(int i = -1; i<2; i++) {
			for(int j = -1; j<2; j++) {
				if(x+j >= 0 && x+j < GameInfo.current.getType().getWidth() && y+i >= 0 && y+i < GameInfo.current.getType().getHeight()) {
					ads.add(new int[] {y+i,x+j});
					squares[y+i][x+j].setPressed(true);
				}
			}
		}
		repaint();
	}
	
	private void removeAdjacents() {
		for(int i=ads.size()-1; i>-1; i--) {
			squares[ads.get(i)[0]][ads.get(i)[1]].setPressed(false);
			ads.remove(i);
		}
		repaint();
	}
	
	private boolean openAdjacents() {
		boolean noMines = true;
		while(ads.size()>0) {
			if(!squares[ads.get(0)[0]][ads.get(0)[1]].isFlagged()) {
				squares[ads.get(0)[0]][ads.get(0)[1]].setOpen(true);
				if(GameInfo.current.getGrid()[ads.get(0)[0]][ads.get(0)[1]].isMine()) {
					squares[ads.get(0)[0]][ads.get(0)[1]].setBlown(true);
					noMines = false;
				}
				if(GameInfo.current.getGrid()[ads.get(0)[0]][ads.get(0)[1]].getAdjacents() == 0) {
					opening(ads.get(0)[1], ads.get(0)[0]);
				}
			}
			ads.remove(0);
		}
		repaint();
		return noMines;
	}
	
}
