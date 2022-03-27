package team3.tetris.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class PauseDialog extends Dialog implements ActionListener {
	JPanel pan;
	JLabel label;
	JButton resume, exit; // Resume, Exit Button
	private boolean isResume = false;
	
	public boolean getResume() {
		return isResume;
	}
	
	public PauseDialog(Frame parent) {
		super(parent, "Pause");
		setSize(350, 300);
		setLocationRelativeTo(null);
		setBackground(Color.BLACK);
		
		pan = new JPanel();
		pan.setBackground(Color.BLACK);
		pan.setLayout(null);
		
		label = new JLabel("PAUSE!");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.WHITE);
		
		
		// resume button
		resume = new JButton("RESUME");
		resume.addActionListener(this);
		resume.setBackground(Color.BLACK);
		resume.setForeground(Color.WHITE);
		// exit button
		exit = new JButton("EXIT");
		exit.addActionListener(this);
		exit.setBackground(Color.BLACK);
		exit.setForeground(Color.WHITE);
		
		
		label.setBounds(92, 70, 150, 30);
		resume.setBounds(30, 170, 122, 30);
		exit.setBounds(182, 170, 122, 30);
		
		pan.add(label);
		pan.add(resume);
		pan.add(exit);
		
		add(pan);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == resume) {
			// isResume = true;													// Error!!!!
			dispose();
		}
		else if(e.getSource() == exit) {
			// 종료 인터페이스 띄우기
			System.exit(0);
		}
	}
}
