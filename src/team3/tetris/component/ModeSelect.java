package team3.tetris.component;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JFrame;

/*
 * 클래스: ModeSelect
 * 기능: Start시 모드 선택 창을 띄움
 */

public class ModeSelect extends JFrame implements ActionListener {
	private KeyListener playerKeyListener;
	Container con;
	JLabel label;
	
	JButton normalMode;
	JButton itemMode;
	JButton colorBlind;
	JButton curBtn;
	
	public ModeSelect() {
		super("ModeSelect");
		setSize(350, 300);
		setLocationRelativeTo(null);
		setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		addWindowFocusListener(new myWindowFocusListener());
		playerKeyListener = new PlayerKeyListener();
		
		con = getContentPane();
		con.setBackground(Color.BLACK);
		con.setLayout(null);
		
		label = new JLabel("Mode Select");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		label.setForeground(Color.WHITE);
		label.setBackground(Color.BLACK);
		label.setFont(new Font("SansSerif", Font.BOLD, 20));
		
		// Normal button
		normalMode = new JButton("Nomral Mode");
		normalMode.addActionListener(this);
		normalMode.addFocusListener(new MyFocusListener());
		normalMode.addKeyListener(playerKeyListener);
		normalMode.setBackground(Color.BLACK);
		normalMode.setForeground(Color.WHITE);
		
		
		// ItemMode button
		itemMode = new JButton("Item Mode");
		itemMode.addActionListener(this);
		itemMode.addFocusListener(new MyFocusListener());
		itemMode.addKeyListener(playerKeyListener);
		itemMode.setBackground(Color.BLACK);
		itemMode.setForeground(Color.WHITE);
		
		
		label.setBounds(0, 0, 350, 50);
		normalMode.setBounds(40, 80, 270, 50);
		itemMode.setBounds(40, 160, 270, 50);
		
		con.add(label);
		con.add(normalMode);
		con.add(itemMode);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == normalMode) {
			Board board = new Board();
			board.setVisible(true);
			board.run();
			dispose();
		}
		else if(e.getSource() == itemMode) {
			ItemBoard board = new ItemBoard();
			board.setVisible(true);
			board.run();
			dispose();
		}
	}
	
	public class PlayerKeyListener implements KeyListener { // key 입력
		@Override
		public void keyTyped(KeyEvent e) {
				
		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			switch(e.getKeyCode()) {
				case KeyEvent.VK_DOWN, KeyEvent.VK_RIGHT:
					curBtn.transferFocus();
					break;
				case KeyEvent.VK_UP, KeyEvent.VK_LEFT:
					curBtn.transferFocusBackward();
					break;
				case KeyEvent.VK_ENTER:
			        curBtn.doClick(); 
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
	
	public class myWindowFocusListener implements WindowFocusListener {

		@Override
		public void windowGainedFocus(WindowEvent e) {
			// TODO Auto-generated method stub
			normalMode.requestFocus();
		}

		@Override
		public void windowLostFocus(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

}