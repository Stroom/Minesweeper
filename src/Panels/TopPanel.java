package Panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import Timers.TimeTimer;

import Borders.DarkLightBorder;
import GameInfo.GameInfo;
import Graphics.SmileyButton;


public class TopPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private NumPanel minePanel;
	private NumPanel timePanel;
	private SmileyButton smiley;
	
	private TimeTimer timer;
	
	TopPanel() {
		
		setTimer(new TimeTimer());
		
		setLayout(new GridBagLayout());
		setBackground(Color.LIGHT_GRAY);
		GridBagConstraints c = new GridBagConstraints();
		
		Dimension size = new Dimension(GameInfo.current.getType().getWidth()*16 + 6,37);
		setMinimumSize(size);
		setMaximumSize(size);
		setPreferredSize(size);
		setSize(size);
		setBorder(new DarkLightBorder(2));
		
		minePanel = new NumPanel();
		c.gridy = 0;
		c.gridx = 0;
		c.insets = new Insets(4,4,4,6);
		c.anchor = GridBagConstraints.LINE_START;
		add(minePanel, c);
		
		JPanel empty = new JPanel();
		empty.setOpaque(false);
		c.gridx = 1;
		c.weightx = 1;
		c.insets = new Insets(0,0,0,0);
		add(empty,c);
		c.weightx = 0;
		
		smiley = new SmileyButton();
		smiley.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {
				if(smiley.isClicked() && e.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK) {
					smiley.setOver(true);
					smiley.repaint();
				}
			}
			public void mouseExited(MouseEvent e) {
				if(smiley.isOver()) {
					smiley.setOver(false);
					smiley.repaint();
				}
			}
			public void mousePressed(MouseEvent e) {
				smiley.setOver(true);
				smiley.setClicked(true);
				smiley.repaint();
			}
			public void mouseReleased(MouseEvent e) {
				if(smiley.isClicked() && smiley.isOver()) {GameInfo.current.reset();}
				smiley.setClicked(false);
				smiley.setOver(false);
				smiley.repaint();
			}
			
		});
		c.gridx = 2;
		c.anchor = GridBagConstraints.CENTER;
		
		add(smiley, c);
		
		empty = new JPanel();
		empty.setOpaque(false);
		c.gridx = 3;
		c.weightx = 1;
		c.insets = new Insets(0,0,0,0);
		add(empty,c);
		c.weightx = 0;
		
		timePanel = new NumPanel();
		
		c.gridx = 4;
		c.insets = new Insets(4,4,4,6);
		c.anchor = GridBagConstraints.LINE_END;
		add(timePanel, c);
		setBeginningNums();
	}
	
	public void setNewSize() {
		Dimension size = new Dimension(GameInfo.current.getType().getWidth()*16 + 6,37);
		setMinimumSize(size);
		setMaximumSize(size);
		setPreferredSize(size);
		setSize(size);
	}
	
	public void setMinePanelNum(int num) {
		minePanel.setNumber(num);
		repaint();
	}
	public void setSecondPanelNum(int num) {
		timePanel.setNumber(num);
		repaint();
	}
	public void setBeginningNums() {
		minePanel.setNumber(GameInfo.current.getType().getMines());
		timePanel.setNumber(1);
		repaint();
	}
	public void setSmileyPressed(boolean value) {
		smiley.setPressed(value);
		smiley.repaint();
	}
	public void setSmileyLost(boolean value) {
		smiley.setLost(value);
		smiley.repaint();
	}
	public void setSmileyWon(boolean value) {
		smiley.setWon(value);
		smiley.repaint();
	}
	public void setSmileyDefault() {
		smiley.setPressed(false);
		smiley.setLost(false);
		smiley.setWon(false);
		smiley.repaint();
	}
	
	public void setTimer(TimeTimer timer) {this.timer = timer;}
	public void startTimer() {this.timer.start();}
	public void stopTimer() {this.timer.stop();}
	
}
