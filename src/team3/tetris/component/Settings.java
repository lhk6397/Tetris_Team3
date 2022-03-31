package team3.tetris.component;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;

public class Settings extends Dialog implements ActionListener {
	JPanel pan;
	JLabel label;
	JButton exit;
	
	
	
	public Settings(Frame parent) {
		super(parent, "Settings");
		setSize(350, 300);
		setLocationRelativeTo(null);
		setBackground(Color.BLACK);
		
		pan = new JPanel();
		pan.setBackground(Color.BLACK);
		pan.setLayout(null);
		
		label = new JLabel("Settings");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.WHITE);
		
		// exit button
		exit = new JButton("EXIT");
		exit.addActionListener(this);
		exit.setBackground(Color.BLACK);
		exit.setForeground(Color.WHITE);
		
		
		label.setBounds(92, 70, 150, 30);
		exit.setBounds(0, 150, 270, 30);
		
		pan.add(label);
		pan.add(exit);
		
		add(pan);
	

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exit) {
			setVisible(false);
		}
	}
}

