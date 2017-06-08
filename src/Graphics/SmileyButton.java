package Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;


public class SmileyButton extends JComponent {
	
	private static final long serialVersionUID = 1L;
	
	private boolean pressed;
	private boolean over;
	private boolean won;
	private boolean lost;
	
	private boolean clicked;
	
	public SmileyButton() {
		Dimension size = new Dimension(26,26);
		setBorder(null);
		
		setPressed(false);
		setOver(false);
		setWon(false);
		setLost(false);
		
		setClicked(false);
		
		setSize(size);
		super.setMinimumSize(size);
		super.setMaximumSize(size);
		super.setPreferredSize(size);
		
		setVisible(true);
	}
	
	protected void paintComponent(Graphics g) {
		//OUTTER RECTANGLE
		g.setColor(Color.GRAY);
		g.drawRect(0, 0, 25, 25);
		
		if(!isOver()) {
			//Default outline and base
			g.setColor(Color.WHITE);
			g.drawLine(1, 1, 1, 23);
			g.drawLine(2, 1, 2, 22);
			g.drawLine(3, 1, 23, 1);
			g.drawLine(3, 2, 22, 2);
			
			g.setColor(Color.GRAY);
			g.drawLine(2, 24, 24, 24);
			g.drawLine(3, 23, 24, 23);
			g.drawLine(24, 2, 24, 22);
			g.drawLine(23, 3, 23, 22);
			
			g.setColor(Color.LIGHT_GRAY);
			g.drawLine(0, 25, 25, 0);
			g.fillRect(3, 3, 20, 20);
		}
		
		if (isOver()){
			//Button down background
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(1, 1, 24, 24);
			g.drawLine(0, 25, 25, 0);
			
			g.setColor(Color.GRAY);
			g.drawLine(1, 1, 1, 24);
			g.drawLine(2, 1, 24, 1);
			
			//Button down face
			g.setColor(Color.YELLOW);
			g.fillOval(6, 6, 16, 16);
			
			g.setColor(Color.BLACK);
			g.drawOval(6, 6, 16, 16);
			
			g.fillRect(11, 11, 2, 2);
			g.fillRect(16, 11, 2, 2);
			
			g.drawLine(12, 18, 16, 18);
			g.drawLine(10, 16, 11, 17);
			g.drawLine(18, 16, 17, 17);
			
		}
		else if(isLost()) {
			// XO face 
			g.setColor(Color.YELLOW);
			g.fillOval(5, 5, 16, 16);
			
			g.setColor(Color.BLACK);
			g.drawOval(5, 5, 16, 16);
			
			g.drawLine(9, 9, 11, 11);
			g.drawLine(9, 11, 11, 9);
			g.drawLine(15, 9, 17, 11);
			g.drawLine(15, 11, 17, 9);
			
			g.drawLine(11, 15, 15, 15);
			g.drawLine(9, 17, 10, 16);
			g.drawLine(17, 17, 16, 16);
		}
		else if(isWon()) {
			//8D face
			g.setColor(Color.YELLOW);
			g.fillOval(5, 5, 16, 16);
			
			g.setColor(Color.BLACK);
			g.drawOval(5, 5, 16, 16);
			
			g.drawLine(11, 17, 15, 17);
			g.drawLine(10, 16, 10, 16);
			g.drawLine(16, 16, 16, 16);
			
			g.drawLine(6, 13, 8, 11);
			g.drawLine(20, 13, 18, 11);
			g.drawLine(9, 10, 17, 10);
			g.fillRect(9, 11, 4, 2);
			g.fillRect(14, 11, 4, 2);
			g.drawLine(10, 13, 11, 13);
			g.drawLine(15, 13, 16, 13);
			g.setColor(new Color(128, 128, 0));
			g.drawLine(9, 13, 9, 13);
			g.drawLine(17, 13, 17, 13);
		}
		else if(isPressed()) {
			//:O face
			g.setColor(Color.YELLOW);
			g.fillOval(5, 5, 16, 16);
			
			g.setColor(Color.BLACK);
			g.drawOval(5, 5, 16, 16);
			
			g.setColor(new Color(128,128,0));
			g.fillRect(9, 9, 3, 3);
			g.fillRect(15, 9, 3, 3);
			
			g.drawLine(11, 15, 11, 17);
			g.drawLine(15, 15, 15, 17);
			
			g.setColor(Color.BLACK);
			g.drawLine(10, 9, 10, 11);
			g.drawLine(9, 10, 11, 10);
			g.drawLine(16, 9, 16, 11);
			g.drawLine(15, 10, 17, 10);
			
			g.drawLine(12, 14, 14, 14);
			g.drawLine(11, 16, 12, 15);
			g.drawLine(15, 16, 14, 15);
			g.drawLine(12, 18, 14, 18);
			g.drawLine(12, 17, 12, 17);
			g.drawLine(14, 17, 14, 17);
			
		}
		else {
			//:) face
			g.setColor(Color.YELLOW);
			g.fillOval(5, 5, 16, 16);
			
			g.setColor(Color.BLACK);
			g.drawOval(5, 5, 16, 16);
			
			g.fillRect(10, 10, 2, 2);
			g.fillRect(15, 10, 2, 2);
			
			g.drawLine(11, 17, 15, 17);
			g.drawLine(9, 15, 10, 16);
			g.drawLine(17, 15, 16, 16);
			
		}
		
		
	}
	
	public void setMaximumSize(Dimension maximumSize) {}
	public void setMinimumSize(Dimension minimumSize) {}
	public void setPreferredSize(Dimension preferredSize) {}
	
	public boolean isPressed() {return pressed;}
	public void setPressed(boolean pressed) {this.pressed = pressed;}
	public boolean isOver() {return over;}
	public void setOver(boolean over) {this.over = over;}
	public boolean isWon() {return won;}
	public void setWon(boolean won) {this.won = won;}
	public boolean isLost() {return lost;}
	public void setLost(boolean lost) {this.lost = lost;}
	public boolean isClicked() {return clicked;}
	public void setClicked(boolean clicked) {this.clicked = clicked;}
	
}
