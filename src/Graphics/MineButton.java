package Graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

import GameInfo.GameInfo;

public class MineButton extends JComponent {
	
	private static final long serialVersionUID = 1L;
	private int x, y;
	private boolean pressed;
	private boolean open;
	private boolean flagged;
	private boolean blown;
	
	public MineButton(int x1, int y1) {
		Dimension size = new Dimension(16,16);
		x = x1;
		y = y1;
		setBorder(null);
		setName("MB");
		
		setSize(size);
		super.setMinimumSize(size);
		super.setPreferredSize(size);
		super.setMaximumSize(size);
		
		setPressed(false);
		setOpen(false);
		setFlagged(false);
		setBlown(false);
		
		setVisible(true);
		
	}
	
	protected void paintComponent(Graphics g) {
		
		
		if(open) {												//Opened field
			if(!blown) {
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(0, 0, getSize().width, getSize().height);
			}
			else {
				g.setColor(Color.RED);
				g.fillRect(0, 0, getSize().width, getSize().height);
			}
			g.setColor(Color.GRAY);
			g.drawLine(0, 0, 0, 15);
			g.drawLine(0, 0, 15, 0);
			
			if(GameInfo.current.getGrid()[y][x].isMine()) {
				g.setColor(Color.BLACK);
				g.fillOval(3, 3, 10, 10);
				g.drawLine(4, 4, 12, 12);
				g.drawLine(4, 12, 12, 4);
				g.drawLine(2, 8, 14, 8);
				g.drawLine(8, 2, 8, 14);
				g.setColor(Color.WHITE);
				g.fillRect(6, 6, 2, 2);
			}
			else {
				int num = GameInfo.current.getGrid()[y][x].getAdjacents();
				switch(num) {
					case 0: break;
					
					case 1: g.setColor(Color.BLUE);
					g.fillRect(5, 11, 7, 2);
					g.fillRect(7, 4, 3, 7);
					g.drawLine(5, 6, 8, 3);
					g.drawLine(6, 6, 9, 3);
					break;
					
					case 2: g.setColor(new Color(0, 128, 0));
					g.fillRect(3, 11, 10, 2);
					g.drawLine(3, 10, 6, 10);
					g.drawLine(4, 9, 8, 9);
					g.drawLine(6, 8, 10, 8);
					g.drawLine(8, 7, 11, 7);
					g.fillRect(10, 4, 3, 3);
					g.fillRect(4, 3, 8, 2);
					g.fillRect(3, 4, 3, 2);
					break;
					
					case 3: g.setColor(Color.RED);
					g.fillRect(3, 3, 9, 2);
					g.fillRect(3, 11, 9, 2);
					g.fillRect(6, 7, 6, 2);
					g.fillRect(10, 4, 3, 3);
					g.fillRect(10, 9, 3, 3);
					break;
					
					case 4: g.setColor(new Color(0, 0, 128));
					g.fillRect(9, 3, 3, 10);
					g.fillRect(3, 7, 10, 2);
					g.fillRect(5, 3, 3, 2);
					g.fillRect(4, 5, 3, 2);
					break;
					
					case 5: g.setColor(new Color(128, 0, 0));
					g.fillRect(3, 3, 10, 2);
					g.fillRect(3, 7, 9, 2);
					g.fillRect(3, 11, 9, 2);
					g.fillRect(3, 5, 3, 2);
					g.fillRect(10, 8, 3, 4);
					break;
					
					case 6: g.setColor(new Color(0, 128, 128));
					g.fillRect(4, 3, 8, 2);
					g.fillRect(3, 4, 3, 8);
					g.fillRect(6, 7, 6, 2);
					g.fillRect(4, 11, 8, 2);
					g.fillRect(10, 8, 3, 4);
					break;
					
					case 7: g.setColor(Color.BLACK);
					g.fillRect(3, 3, 10, 2);
					g.fillRect(10, 5, 3, 2);
					g.fillRect(9, 7, 3, 2);
					g.fillRect(8, 9, 3, 2);
					g.fillRect(7, 11, 3, 2);
					break;
					
					case 8: g.setColor(new Color(128, 128, 128));
					g.fillRect(4, 3, 8, 2);
					g.fillRect(4, 7, 8, 2);
					g.fillRect(4, 11, 8, 2);
					g.fillRect(3, 4, 3, 3);
					g.fillRect(10, 4, 3, 3);
					g.fillRect(3, 9, 3, 3);
					g.fillRect(10, 9, 3, 3);
					break;
					
					default: break;
				}
			}
			
		}
		else {													//Closed field
			if(flagged && blown) {									//Wrong flag.
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(0, 0, getSize().width, getSize().height);
				g.setColor(Color.GRAY);
				g.drawLine(0, 0, 0, 15);
				g.drawLine(0, 0, 15, 0);
				
				g.setColor(Color.BLACK);
				g.fillOval(3, 3, 10, 10);
				g.drawLine(4, 4, 12, 12);
				g.drawLine(4, 12, 12, 4);
				g.drawLine(2, 8, 14, 8);
				g.drawLine(8, 2, 8, 14);
				g.setColor(Color.WHITE);
				g.fillRect(6, 6, 2, 2);
				
				g.setColor(Color.RED);
				g.drawLine(2, 3, 13, 14);
				g.drawLine(3, 3, 14, 14);
				g.drawLine(2, 14, 13, 3);
				g.drawLine(3, 14, 14, 3);
			}
			else if(flagged) {										//Flagged
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, getSize().width, getSize().height);
				g.setColor(Color.GRAY);
				int[] x = {0, 16, 16};
				int[] y = {16, 0, 16};
				g.fillPolygon(x, y, 3);
				g.setColor(Color.LIGHT_GRAY);
				g.drawLine(0, 15, 15, 0);
				g.fillRect(2, 2, 12, 12);
				
				g.setColor(Color.BLACK);
				g.fillRect(4, 11, 8, 2);
				g.drawLine(6, 10, 9, 10);
				g.drawLine(8, 8, 8, 9);
				g.setColor(Color.RED);
				g.fillRect(7, 3, 2, 5);
				g.fillRect(5, 4, 2, 3);
				g.drawLine(4, 5, 4, 5);
			}
			else if(pressed && !open) {								//Pressed, not clicked.
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(0, 0, getSize().width, getSize().height);
				g.setColor(Color.GRAY);
				g.drawLine(0, 0, 0, 15);
				g.drawLine(0, 0, 15, 0);
			}
			else {													//Not pressed.
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, getSize().width, getSize().height);
				g.setColor(Color.GRAY);
				int[] x = {0, 16, 16};
				int[] y = {16, 0, 16};
				g.fillPolygon(x, y, 3);
				g.setColor(Color.LIGHT_GRAY);
				g.drawLine(0, 15, 15, 0);
				g.fillRect(2, 2, 12, 12);
			}
			
		}
		
	}
	
	public void setMaximumSize(Dimension maximumSize) {}
	public void setMinimumSize(Dimension minimumSize) {}
	public void setPreferredSize(Dimension preferredSize) {}
	
	public boolean isPressed() {return pressed;}
	public void setPressed(boolean pressed) {this.pressed = pressed;}
	public boolean isOpen() {return open;}
	public void setOpen(boolean open) {this.open = open;}
	public boolean isFlagged() {return flagged;}
	public void setFlagged(boolean flag) {this.flagged = flag;}
	public boolean isBlown() {return blown;}
	public void setBlown(boolean blown) {this.blown = blown;}
}
