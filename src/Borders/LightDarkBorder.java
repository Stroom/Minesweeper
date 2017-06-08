package Borders;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.AbstractBorder;

public class LightDarkBorder extends AbstractBorder {
	
	private static final long serialVersionUID = 1L;
	protected int lineWidth;
	
	public LightDarkBorder(int width) {lineWidth = width;}
	
	public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
		g.setColor(new Color(160,160,160));
		for(int i = 0; i < lineWidth; i++) {
			g.drawRect(x+i, y+i, width-2*i-1, height-2*i-1);
		}
		g.setColor(Color.WHITE);
		for(int i = 0; i < lineWidth; i++) {
			g.drawLine(x+i, y+i, width-i-2, y+i);
			g.drawLine(x+i, y+i, x+i, height-i-2);
		}
	}

	public Insets getBorderInsets(Component c) {return new Insets(lineWidth,lineWidth,lineWidth,lineWidth);}
	
	public boolean isBorderOpaque() {return false;}
	
}
