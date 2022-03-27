package team3.tetris.component;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Socreboard extends JFrame implements ActionListener{
	JPanel Container, labelPanel, textPanel;
	JLabel label1;
	
	public Socreboard(Frame parent) {
		super("ScoreBoard");
		setLocationRelativeTo(null);
		setBackground(Color.BLACK);
		
		
		Container = new JPanel();
		Container.setBackground(Color.BLACK);
		Container.setLayout(new BoxLayout(Container, BoxLayout.Y_AXIS));
		
		labelPanel = new JPanel();
		labelPanel.setBackground(Color.BLACK);
		
		label1 = new JLabel("Game Over!");
		label1.setHorizontalAlignment(JLabel.CENTER);
		label1.setForeground(Color.WHITE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
}
