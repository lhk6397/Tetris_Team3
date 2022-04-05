package team3.tetris.component;

import team3.tetris.record.RecordDTO;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Scoreboard Frame에서 이름을 입력받기 위한 JPanel
 */

public class InputPanel extends JPanel implements ActionListener {
	private boolean isSubmitted = false;
	private boolean isTextField = true;
	JPanel pan;
	Scoreboard parent;
	JLabel nameL;
	JTextField nameT;
	JButton okBtn;
	JButton noBtn;
	JButton curBtn;
	
	public InputPanel(Scoreboard parent){
		this.parent = parent;
		setBackground(Color.BLACK);
		setFocusable(true);
		
		pan = new JPanel();
		pan.setBackground(Color.BLACK);
		Box inputBox = Box.createHorizontalBox();
		Box btnBox = Box.createHorizontalBox();
		
		nameL = new JLabel("Input your name (1 ~ 20): ");
		nameL.setBackground(Color.BLACK);
		nameL.setForeground(Color.WHITE);
		
		nameT = new JTextField(20);
		nameT.setBackground(Color.BLACK);
		nameT.setForeground(Color.WHITE);
		nameT.addActionListener(this);
		nameT.addKeyListener(new PlayerKeyListener());
		nameT.addFocusListener(new MyFocusListener());
		
		
		inputBox.add(nameL);
		inputBox.add(nameT);
		
		okBtn = new JButton("Ok");
		okBtn.setBackground(Color.BLACK);
		okBtn.setForeground(Color.WHITE);
		
		noBtn = new JButton("Cancel");
		noBtn.setBackground(Color.BLACK);
		noBtn.setForeground(Color.WHITE);

		// Add ActionListener
		okBtn.addActionListener(this);
		okBtn.addKeyListener(new PlayerKeyListener());
		okBtn.addFocusListener(new MyFocusListener());
		noBtn.addActionListener(this);
		noBtn.addKeyListener(new PlayerKeyListener());
		noBtn.addFocusListener(new MyFocusListener());
		
		btnBox.add(okBtn);
		btnBox.add(Box.createHorizontalStrut(100));
		btnBox.add(noBtn);
		
		pan.setLayout(new BoxLayout(pan, BoxLayout.Y_AXIS));
		pan.add(inputBox);
		pan.add(Box.createVerticalStrut(10));
		pan.add(btnBox);
		
		add(pan);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == okBtn) {
			isSubmitted = true;
		}
		else if(e.getSource() == noBtn) {
			isSubmitted = false;
		}
		try {
			parent.checkSubmission(isSubmitted, nameT.getText());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public class PlayerKeyListener implements KeyListener { // key 입력
		@Override
		public void keyTyped(KeyEvent e) {
				
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			switch(e.getKeyCode()) {
				case KeyEvent.VK_DOWN, KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT:
					if(!isTextField) {
						if(curBtn == okBtn) {
							curBtn.transferFocus();
						} else {
							curBtn.transferFocusBackward();
						}
					}
					break;
				case KeyEvent.VK_ENTER:
//					if(isTextField) {
//						// 이름 기록 함수
//						curBtn.requestFocus();
//					} else {
//						curBtn.doClick();
//					}
					// 메모리 문제로 enter키 누르면 강제 종료 됨...
					break;
				default:
					break;
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {}

	}
	
	public class MyFocusListener implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			if(e.getComponent() != nameT) {
				// System.out.println(e.getComponent());
				curBtn = (JButton)e.getComponent();
				curBtn.setBackground(Color.WHITE);
				curBtn.setForeground(Color.BLACK);
			} else {
				isTextField = true;
			}
		}

		@Override
		public void focusLost(FocusEvent e) {
			if(e.getComponent() != nameT) {
				curBtn.setBackground(Color.BLACK);
				curBtn.setForeground(Color.WHITE);
			} else {
				isTextField = false;
			}
		}
	}
	
}