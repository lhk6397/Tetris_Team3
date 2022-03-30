package team3.tetris.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JButton;

/*
 * 쿨래스: ExitDialog
 * 게임 종료 시 띄우는 Dialog
 */

public class ExitDialog extends Dialog implements ActionListener {
	JPanel pan;
	JLabel label;
	JButton startMenu, exit; // startMenu, Exit Button
	
	public ExitDialog(Frame parent) {
		super(parent, "ScoreBoard");
		setSize(350, 300);
		setLocationRelativeTo(null);
		setBackground(Color.BLACK);
		
		pan = new JPanel();
		pan.setBackground(Color.BLACK);
		pan.setLayout(null);
		//new BoxLayout(pan, BoxLayout.Y_AXIS)
		label = new JLabel("Successfully Save!");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.WHITE);
		
		// resume button
		startMenu = new JButton("Start Menu");
		startMenu.addActionListener(this);
		startMenu.setBackground(Color.BLACK);
		startMenu.setForeground(Color.WHITE);
		// exit button
		exit = new JButton("EXIT");
		exit.addActionListener(this);
		exit.setBackground(Color.BLACK);
		exit.setForeground(Color.WHITE);
		
		
		label.setBounds(92, 70, 150, 30);
		startMenu.setBounds(30, 170, 122, 30);
		exit.setBounds(182, 170, 122, 30);
		
		pan.add(label);
		pan.add(startMenu);
		pan.add(exit);
		
		add(pan);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startMenu) {
			// startMenu 띄우기
			dispose();
		}
		else if(e.getSource() == exit) {
			System.exit(0);
		}
	}
	
}
