package team3.tetris.component;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MainMenu extends Dialog implements ActionListener {
	JPanel pan;
	JLabel label;
	JButton start;
	JButton settings;
	JButton scoreBoard;
	JButton exit;
	
	
	
	public MainMenu(Frame parent) {
		super(parent, "StartMenu");
		setSize(350, 300);
		setLocationRelativeTo(null);
		setBackground(Color.BLACK);
		
		pan = new JPanel();
		pan.setBackground(Color.BLACK);
		pan.setLayout(null);
		
		label = new JLabel("Tetris");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.WHITE);
		
		// start button
		start = new JButton("START");
		start.addActionListener(this);
		start.setBackground(Color.BLACK);
		start.setForeground(Color.WHITE);
		
		// settings button
		settings = new JButton("SETTINGS인데 누르지말것");
		settings.addActionListener(this);
		settings.setBackground(Color.BLACK);
		settings.setForeground(Color.WHITE);

		// scoreBoard button
		scoreBoard = new JButton("SCOREBOARD");
		scoreBoard.addActionListener(this);
		scoreBoard.setBackground(Color.BLACK);
		scoreBoard.setForeground(Color.WHITE);
		
		// exit button
		exit = new JButton("EXIT");
		exit.addActionListener(this);
		exit.setBackground(Color.BLACK);
		exit.setForeground(Color.WHITE);
		
		
		label.setBounds(33, 0, 270, 30);
		start.setBounds(33, 30, 270, 30);
		settings.setBounds(33, 70, 270, 30);
		scoreBoard.setBounds(33, 110, 270, 30);
		exit.setBounds(33, 150, 270, 30);
		
		pan.add(label);
		pan.add(start);
		pan.add(settings);
		pan.add(scoreBoard);
		pan.add(exit);
		
		add(pan);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == start) {
			// isStart = true;
			new Board().setVisible(true);
			setVisible(false);
		}
		else if(e.getSource() == settings) {
			// 세팅 인터페이스 띄우기
			setVisible(false);
		}
		else if(e.getSource() == scoreBoard) {
			// 스코어보드 인터페이스 띄우기
			new Scoreboard().setVisible(true);
			setVisible(false);
		}
		else if(e.getSource() == exit) {
			// 종료 인터페이스 띄우기
			System.exit(0);
		}
	}
}

