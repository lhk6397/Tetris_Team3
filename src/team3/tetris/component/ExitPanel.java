package team3.tetris.component;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;

/*
 * Scoreboard Frame에서 isSubmitted가 true일 경우 나타나는 JPanel
 */

public class ExitPanel extends JPanel implements ActionListener{
	JPanel pan;
	JLabel label;
	JButton mainMenu;
	JButton exit;
	JButton curBtn;
	
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
		mainMenu.addKeyListener(new PlayerKeyListener());
		mainMenu.addFocusListener(new MyFocusListener());
		
		exit.addActionListener(this);
		exit.addKeyListener(new PlayerKeyListener());
		exit.addFocusListener(new MyFocusListener());
		
		btnBox.add(mainMenu);
		btnBox.add(Box.createHorizontalStrut(100));
		btnBox.add(exit);
		
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		pan.add(textBox);
		pan.add(Box.createVerticalStrut(10));
		pan.add(btnBox);
		
		add(pan);
		
		mainMenu.requestFocus(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == mainMenu) {
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
	
	public class PlayerKeyListener implements KeyListener {
		@Override
		public void keyTyped(KeyEvent e) {
				
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			switch(e.getKeyCode()) {
				case KeyEvent.VK_DOWN, KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT:
						if(curBtn == mainMenu) {
							curBtn.transferFocus();
						} else {
							curBtn.transferFocusBackward();
						}
					break;
				case KeyEvent.VK_ENTER:
			        curBtn.doClick(); 
					break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {}

	}
	
	public class MyFocusListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			curBtn = (JButton)e.getComponent();
			curBtn.setBackground(Color.WHITE);
			curBtn.setForeground(Color.BLACK);
		}

		@Override
		public void focusLost(FocusEvent e) {
			curBtn.setBackground(Color.BLACK);
			curBtn.setForeground(Color.WHITE);
		}
		
	}

}
