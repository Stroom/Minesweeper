package Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;


public class NumLabel extends JComponent {
	
	private static final long serialVersionUID = 1L;
	
	private int number;
	
	public NumLabel() {
		Dimension size = new Dimension(13,23);
		setBorder(null);
		
		number = 0;
		
		setSize(size);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		
		setVisible(true);
	}
	
	protected void paintComponent(Graphics g) {
		//Default
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 13, 23);
		
		g.setColor(new Color(128,0,0));
		
		g.drawLine(1, 2, 3, 4);
		g.drawLine(1, 4, 3, 6);
		g.drawLine(1, 6, 3, 8);
		g.drawLine(1, 8, 2, 9);
		g.drawLine(1, 10, 1, 10);
		
		g.drawLine(1, 12, 3, 14);
		g.drawLine(1, 14, 3, 16);
		g.drawLine(1, 16, 3, 18);
		g.drawLine(1, 18, 2, 19);
		g.drawLine(1, 20, 1, 20);
		
		g.drawLine(3, 1, 5, 3);
		g.drawLine(5, 1, 7, 3);
		g.drawLine(7, 1, 8, 2);
		g.drawLine(9, 1, 9, 1);
		
		g.drawLine(11, 2, 9, 4);
		g.drawLine(11, 4, 9, 6);
		g.drawLine(11, 6, 9, 8);
		g.drawLine(11, 8, 10, 9);
		g.drawLine(11, 10, 11, 10);
		
		g.drawLine(11, 12, 9, 14);
		g.drawLine(11, 14, 9, 16);
		g.drawLine(11, 16, 9, 18);
		g.drawLine(11, 18, 10, 19);
		g.drawLine(11, 20, 11, 20);
		
		g.drawLine(3, 21, 5, 19);
		g.drawLine(5, 21, 7, 19);
		g.drawLine(7, 21, 8, 20);
		g.drawLine(9, 21, 9, 21);
		
		g.drawLine(3, 11, 4, 12);
		g.drawLine(4, 10, 6, 12);
		g.drawLine(6, 10, 8, 12);
		g.drawLine(8, 10, 9, 11);
		//Number
		g.setColor(Color.RED);
		if(number != 1 && number != 4) {
			g.drawLine(2, 1, 10, 1);
			g.drawLine(3, 2, 9, 2);
			g.drawLine(4, 3, 8, 3);
		}
		if(number != 5 && number != 6) {
			g.drawLine(11, 2, 11, 10);
			g.drawLine(10, 3, 10, 9);
			g.drawLine(9, 4, 9, 8);
		}
		if(number != 2) {
			g.drawLine(11, 12, 11, 20);
			g.drawLine(10, 13, 10, 19);
			g.drawLine(9, 14, 9, 18);
		}
		if(number != 1 && number != 4 && number != 7) {
			g.drawLine(2, 21, 10, 21);
			g.drawLine(3, 20, 9, 20);
			g.drawLine(4, 19, 8, 19);
		}
		if(number == 0 || number == 2 || number == 6 || number == 8) {
			g.drawLine(1, 12, 1, 20);
			g.drawLine(2, 13, 2, 19);
			g.drawLine(3, 14, 3, 18);
		}
		if(number != 1 && number != 2 && number != 3 && number != 7) {
			g.drawLine(1, 2, 1, 10);
			g.drawLine(2, 3, 2, 9);
			g.drawLine(3, 4, 3, 8);
		}
		if(number != 0 && number != 1 && number != 7) {
			g.drawLine(3, 10, 9, 10);
			g.drawLine(2, 11, 10, 11);
			g.drawLine(3, 12, 9, 12);
		}
		
	}
	
	public void setMaximumSize(Dimension maximumSize) {}
	public void setMinimumSize(Dimension minimumSize) {}
	public void setPreferredSize(Dimension preferredSize) {}
	
	public int getNumber() {return number;}
	public void setNumber(int number) {this.number = number;}
	
}
