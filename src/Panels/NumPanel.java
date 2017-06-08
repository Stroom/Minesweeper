package Panels;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;

import Borders.DarkLightBorder;
import Graphics.NumLabel;


public class NumPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private int number;
	
	private NumLabel xoo;
	private NumLabel oxo;
	private NumLabel oox;
	
	NumPanel() {
		Dimension size = new Dimension(41,25);
		setBorder(new DarkLightBorder(1));
		
		setLayout(new GridLayout(1,3));
		
		xoo = new NumLabel();
		add(xoo);
		oxo = new NumLabel();
		add(oxo);
		oox = new NumLabel();
		add(oox);
		
		setSize(size);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
		
		setVisible(true);
	}
	
	public int getNumber() {return number;}
	public void setNumber(int num) {
		this.number = num;
		
		if(number >= 999) {
			oox.setNumber(9);
			oxo.setNumber(9);
			xoo.setNumber(9);
		}
		else {
			int n;
			n = number % 10;
			oox.setNumber(n);
			n = number / 10 %10;
			oxo.setNumber(n);
			n = number / 100;
			xoo.setNumber(n);
		}
	}
	
	
}
