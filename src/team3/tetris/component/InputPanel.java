package team3.tetris.component;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InputPanel extends JPanel implements ActionListener {
	private boolean isSubmitted = false;
	JPanel pan;
	Scoreboard parent;
	JLabel nameL;
	JTextField nameT;
	JButton okBtn;
	JButton noBtn;
	
	public InputPanel(Scoreboard parent){
		this.parent = parent;
		setBackground(Color.BLACK);
		
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
		noBtn.addActionListener(this);
		
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
		parent.checkSubmission(isSubmitted);
	}
}