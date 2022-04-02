package team3.tetris.component;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

/*
 * 쿨래스: ExitDialog
 * 게임 종료 시 띄우는 Dialog
 */

public class ExitPanel extends JPanel implements ActionListener{
	JPanel pan;
	JLabel label;
	JButton mainMenu;
	JButton exit;
	
	Board parentBoard;
	Scoreboard parent;
	public ExitPanel(Board board, Scoreboard parent) {
		this.parentBoard= board;
		this.parent = parent;
		setBackground(Color.BLACK);
		
		pan = new JPanel();
		pan.setBackground(Color.BLACK);
		
		Box textBox = Box.createHorizontalBox();
		Box btnBox = Box.createHorizontalBox();

		label = new JLabel("Successfully Save!");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.WHITE);
		
		textBox.add(label);
		
		// resume button
		mainMenu = new JButton("Main Menu");
		mainMenu.setBackground(Color.BLACK);
		mainMenu.setForeground(Color.WHITE);
		// exit button
		exit = new JButton("EXIT");
		exit.setBackground(Color.BLACK);
		exit.setForeground(Color.WHITE);
		
		mainMenu.addActionListener(this);
		exit.addActionListener(this);
		
		btnBox.add(mainMenu);
		btnBox.add(Box.createHorizontalStrut(100));
		btnBox.add(exit);
		
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		pan.add(textBox);
		pan.add(Box.createVerticalStrut(10));
		pan.add(btnBox);
		
		add(pan);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mainMenu) {
			// mainMenu 띄우기
			if(parentBoard != null) {
				parentBoard.dispose();
			}
			parent.dispose();
			new MainMenu().setVisible(true);
		}
		else if(e.getSource() == exit) {
			System.exit(0);
		}
	}
}
