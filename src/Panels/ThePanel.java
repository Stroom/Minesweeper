package Panels;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JPanel;

import Borders.LightDarkBorder;


public class ThePanel extends JPanel {	//http://www.javapractices.com/topic/TopicAction.do?Id=160 timer
	
	private static final long serialVersionUID = 1L;
	
	private static TopPanel top;
	private static GamePanel mid;
	
	public ThePanel() {
		
		setBackground(Color.LIGHT_GRAY);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		setBorder(new LightDarkBorder(3));
		
		
		top = new TopPanel();
		
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(6,6,6,6);
		add(top, c);
		
		mid = new GamePanel();
		
		c.gridy = 1;
		c.insets = new Insets(0,6,6,6);
		add(mid, c);
	}
	
	public static TopPanel getTop() {return top;}
	public static GamePanel getMid() {return mid;}
	
}
