package Timers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import Panels.ThePanel;

public class TimeTimer {
	
	private Timer timer;
	
	public TimeTimer() {
		timer = new Timer(100, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThePanel.getTop().setSecondPanelNum((int) (1 + (System.currentTimeMillis() - GameInfo.GameInfo.current.getTimeStarted()) /1000));
			}
		});
	}
	
	public void start() {timer.start();}
	
	public void stop() {timer.stop();}
}
