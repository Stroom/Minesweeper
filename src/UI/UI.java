package UI;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextField;

import GameInfo.GameInfo;
import GameInfo.GameType;
import Panels.ThePanel;


public class UI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private static Container contentPane;
	public static ThePanel thePanel;
	
	public UI() {
		setTitle("Minesweeper by Stroom");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLocationRelativeTo(null);
		
		setMenu();
		
		contentPane = getContentPane();
		contentPane.add(thePanel);
		contentPane.validate();
		
		setFocusable(true);
		setResizable(false);
		setVisible(true);
		pack();
	}
	
	private void setMenu() {
		JMenuBar mb = new JMenuBar();
		JMenu menu;
		JMenuItem item;
		menu = new JMenu("Game");
		item = new JMenuItem("New Game");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameInfo.current.reset();
			}
		});
		menu.add(item);
		
		menu.addSeparator();
		
		ButtonGroup group = new ButtonGroup();
		JRadioButtonMenuItem rbItem;
		rbItem = new JRadioButtonMenuItem("Beginner");
		rbItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameInfo.current.setType(new GameType(0));
				GameInfo.current.reset();
				setNewSizes();
				pack();
			}
		});
		group.add(rbItem);
		menu.add(rbItem);
		
		rbItem = new JRadioButtonMenuItem("Intermediate");
		rbItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameInfo.current.setType(new GameType(1));
				GameInfo.current.reset();
				setNewSizes();
				pack();
			}
		});
		group.add(rbItem);
		menu.add(rbItem);
		
		rbItem = new JRadioButtonMenuItem("Expert");
		rbItem.setSelected(true);
		rbItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GameInfo.current.setType(new GameType(2));
				GameInfo.current.reset();
				setNewSizes();
				pack();
			}
		});
		group.add(rbItem);
		menu.add(rbItem);
		
		rbItem = new JRadioButtonMenuItem("Custom...");
		rbItem.setSelected(true);
		rbItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customGameDialog();
				GameInfo.current.reset();
				setNewSizes();
				pack();
			}
		});
		group.add(rbItem);
		menu.add(rbItem);
		
		menu.addSeparator();
		
		item = new JMenuItem("Exit");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.add(item);
		mb.add(menu);
		
		menu = new JMenu("Options");
		item = new JMenuItem("Something here");
		item.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Something here Clicked");
			}
		});
		menu.add(item);
		
		mb.add(menu);
		
		setJMenuBar(mb);
	}
	
	private void setNewSizes() {
		ThePanel.getTop().setNewSize();
		ThePanel.getMid().setNewSize();
	}
	
	public void customGameDialog() {
		JTextField height = new JTextField();
		JTextField width = new JTextField();
		JTextField mines = new JTextField();
		
		height.setText(GameInfo.current.getType().getHeight() + "");
		width.setText(GameInfo.current.getType().getWidth() + "");
		mines.setText(GameInfo.current.getType().getMines() + "");
		
		height.setColumns(4);
		width.setColumns(4);
		mines.setColumns(4);
		
		JOptionPane op = new JOptionPane(
			new Object[] {"Height:", height, "Width", width, "Mines:", mines,},
			JOptionPane.PLAIN_MESSAGE,
			JOptionPane.OK_CANCEL_OPTION,
			null,
			null);
		
		JDialog dialog = op.createDialog(null, "Custom Field");
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		dialog.setVisible(true);
		
		int result = ((Integer)op.getValue()).intValue();
		
		if (result == JOptionPane.OK_OPTION) {
			customGameOkPressed(height.getText(), width.getText(), mines.getText());
			dialog.dispose();
		}
	}
	
	private void customGameOkPressed(String height, String width, String mines) {
		int h = -1;
		int w = -1;
		int m = -1;
		
		try {
			h = Integer.parseInt(height);
		}
		catch (NumberFormatException ex) {}
		
		try {
			w = Integer.parseInt(width);
		}
		catch (NumberFormatException ex) {}
		
		try {
			m = Integer.parseInt(mines);
		}
		catch (NumberFormatException ex) {}
		
		if (h != -1 && w != -1 && m != -1) {
			GameInfo.current.setType(new GameType(w,h,m));
		}
	}
	
	public static void main(String[] args) {
		
		GameInfo.current = new GameInfo();
		thePanel = new ThePanel();
		
		new UI();
	}
	
}
