package team3.tetris.component;

import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Scoreboard extends JFrame implements ActionListener{
	JPanel Container, labelPanel, scorePanel, inputPanel;
	JLabel label1, label2;
	JTextField nameT;
	JButton okBtn, noBtn;
	public Scoreboard() {
		super("ScoreBoard");
		setSize(500, 500);
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
		
		label2 = new JLabel("Scoreboard");
		label2.setHorizontalAlignment(JLabel.LEFT);
		label2.setForeground(Color.WHITE);
		
		labelPanel.add(label1);
		labelPanel.add(label2);
		
		scorePanel = new JPanel();
		scorePanel.setBackground(Color.BLACK);
		
		// JTable
		String header[] = {"순위", "이름", "점수", "난이도"};
		String contents[][] = {
				{"1", "고지완", "100", "easy"}
				};
		JTable scoreboard = new JTable(contents, header);
		scoreboard.setBackground(Color.BLACK);
		scoreboard.setForeground(Color.WHITE);
		/*
		 * contents: 1인 정보 (순위, 이름, 점수, 난이도)
		 * score.txt 파일을 불러와 2차원 배열 형태의 contents에 내용을 담도록 해야 함.
		 */
		scorePanel.add(scoreboard);
		
		inputPanel = new JPanel();
		inputPanel.setBackground(Color.BLACK);
		JLabel nameL = new JLabel("Input your name (1 ~ 20)");
		nameL.setBackground(Color.BLACK);
		nameL.setForeground(Color.WHITE);
		nameT = new JTextField(20);
		nameT.setBackground(Color.BLACK);
		nameL.setForeground(Color.WHITE);
		okBtn = new JButton("Ok");
		okBtn.setBackground(Color.BLACK);
		okBtn.setForeground(Color.WHITE);
		noBtn = new JButton("Cancel");
		noBtn.setBackground(Color.BLACK);
		noBtn.setForeground(Color.WHITE);
		
		// Add ActionListener
		okBtn.addActionListener(this);
		noBtn.addActionListener(this);
		
		inputPanel.add(nameL);
		inputPanel.add(nameT);
		inputPanel.add(okBtn);
		inputPanel.add(noBtn);
		
		Container.add(labelPanel);
		Container.add(scorePanel);
		Container.add(inputPanel);
		add(Container);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == okBtn) {
			/*			 
			 time = 
			 name =  nameT.getText() 값
			 score = ?
			 difficulty = ?
			 time, name, score, difficulty 값 scoreboard.txt에 저장
			 
			 save()
			 read() // 파일 불러오기 -> 갱신된 결과 한번 더 보여줌
			 */
			ExitDialog ed = new ExitDialog(this); // 게임 종료 모달 창 띄우기
			ed.setVisible(true);
		}
		else if(e.getSource() == noBtn) {
			ExitDialog ed = new ExitDialog(this); // Successfully save가 아닌 다른 label 띄우기
			ed.setVisible(true);
		}
	}
}
