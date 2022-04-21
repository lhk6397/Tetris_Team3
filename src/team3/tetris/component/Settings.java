package team3.tetris.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.*;
import java.awt.Window.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import team3.tetris.component.*;

public class Settings extends JFrame implements ActionListener {

	JPanel pan;
	JLabel label;
	JLabel resetLeft;
	JLabel resetRight;
	JLabel resetDown;
	JLabel resetRotate;
	JLabel resetHardDrop;
	JLabel resetPause;
	JRadioButton small;
	JRadioButton medium;
	JRadioButton large;
	JRadioButton easy;
	JRadioButton normal;
	JRadioButton hard;
	JRadioButton none;
	JRadioButton colorBlindness;
	JButton resetSettingsButtonScoreBoard;
	JButton resetLeftButton;
	JButton resetRightButton;
	JButton resetDownButton;
	JButton resetRotateButton;
	JButton resetHardDropButton;
	JButton resetPauseButton;
	JButton resetSettingsButton;
	JButton resetScoreButton;
	JButton confirm;
	JLabel screenSizeLabel;
	JLabel blindnessLabel;
	JButton curBtn;

	public Settings() {
		setSize(300, 600);
		setBackground(Color.BLACK);
		
		
		pan = new JPanel();
		pan.setBackground(Color.BLACK);
		pan.setLayout(null);
		
		label = new JLabel("Settings");
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("SansSerif", Font.BOLD, 20));

		// small button
		small = new JRadioButton("작게",true);
		small.setBackground(Color.BLACK);
		small.setForeground(Color.WHITE);
		
		// medium button
		medium = new JRadioButton("중간",true);
		medium.setBackground(Color.BLACK);
		medium.setForeground(Color.WHITE);
		
		// large button
		large = new JRadioButton("크게",true);
		large.setBackground(Color.BLACK);
		large.setForeground(Color.WHITE);
		
		// easy button
		easy = new JRadioButton("쉬움",true);
		easy.setBackground(Color.BLACK);
		easy.setForeground(Color.WHITE);
		
		// normal button
		normal = new JRadioButton("보통",true);
		normal.setBackground(Color.BLACK);
		normal.setForeground(Color.WHITE);
		
		// hard button
		hard = new JRadioButton("어려움",true);
		hard.setBackground(Color.BLACK);
		hard.setForeground(Color.WHITE);

		// none button
		none = new JRadioButton("기본",true);
		none.setBackground(Color.BLACK);
		none.setForeground(Color.WHITE);

		// colorBlindness button
		colorBlindness = new JRadioButton("색맹",true);
		colorBlindness.setBackground(Color.BLACK);
		colorBlindness.setForeground(Color.WHITE);
		
		//왼쪽이동 label
		resetLeft = new JLabel("왼쪽이동");
		resetLeft.setForeground(Color.WHITE);
		
		//왼쪽이동 button
		resetLeftButton = new JButton("");
		resetLeftButton.setForeground(Color.BLACK);
		
		//오른쪽이동 label
		resetRight = new JLabel("오른쪽이동");
		resetRight.setForeground(Color.WHITE);

		//오른쪽이동 button
		resetRightButton = new JButton("");
		resetRightButton.setForeground(Color.BLACK);
		
		//아래이동 label
		resetDown = new JLabel("아래이동");
		resetDown.setForeground(Color.WHITE);

		//아래이동 button
		resetDownButton = new JButton("");
		resetDownButton.setForeground(Color.BLACK);
		
		//블럭회전 label
		resetRotate = new JLabel("블럭회전");
		resetRotate.setForeground(Color.WHITE);

		//블럭회전 button
		resetRotateButton = new JButton("");
		resetRotateButton.setForeground(Color.BLACK);
		
		//하드드롭 label
		resetHardDrop = new JLabel("하드드롭");
		resetHardDrop.setForeground(Color.WHITE);

		//하드드롭 button
		resetHardDropButton = new JButton("");
		resetHardDropButton.setForeground(Color.BLACK);
		
		//일시정지 label
		resetPause = new JLabel("일시정지");
		resetPause.setForeground(Color.WHITE);

		//일시정지 button
		resetPauseButton = new JButton("");
		resetPauseButton.setForeground(Color.BLACK);

		//점수초기화 button
		resetScoreButton = new JButton("점수초기화");
		resetScoreButton.setForeground(Color.BLACK);
		
		//설정 초기화 button
		resetSettingsButton = new JButton("설정초기화");
		resetSettingsButton.setForeground(Color.BLACK);
		
		//나가기 button
		confirm = new JButton("나가기");
		confirm.setForeground(Color.BLACK);
		
		//위치
		label.setBounds(5, 0, 270, 30);
		small.setBounds(33, 30, 70, 30);
		medium.setBounds(103, 30, 70, 30);
		large.setBounds(173, 30, 70, 30);
		easy.setBounds(33, 60, 70, 30);
		normal.setBounds(103, 60, 70, 30);
		hard.setBounds(173, 60, 70, 30);
		resetLeft.setBounds(33, 110, 70, 30);
		resetRight.setBounds(33, 150, 70, 30);
		resetDown.setBounds(33, 190, 70, 30);
		resetRotate.setBounds(33, 230, 70, 30);
		resetHardDrop.setBounds(33, 270, 70, 30);
		resetPause.setBounds(33, 310, 70, 30);
		resetLeftButton.setBounds(133, 110, 70, 30);
		resetRightButton.setBounds(133, 150, 70, 30);
		resetDownButton.setBounds(133, 190, 70, 30);
		resetRotateButton.setBounds(133, 230, 70, 30);
		resetHardDropButton.setBounds(133, 270, 70, 30);
		resetPauseButton.setBounds(133, 310, 70, 30);
		resetScoreButton.setBounds(65, 350, 150, 30);
		none.setBounds(70, 390, 90, 30);
		colorBlindness.setBounds(190, 390, 90, 30);
		resetSettingsButton.setBounds(30, 500, 120, 30);
		confirm.setBounds(170, 500, 90, 30);

		pan.add(label);
		pan.add(small);
		pan.add(medium);
		pan.add(large);
        ButtonGroup groupScreenSize = new ButtonGroup();
        groupScreenSize.add(small);
        groupScreenSize.add(medium);
        groupScreenSize.add(large);
		pan.add(easy);
		pan.add(normal);
		pan.add(hard);
        ButtonGroup groupDifficulty = new ButtonGroup();
        groupDifficulty.add(easy);
        groupDifficulty.add(normal);
        groupDifficulty.add(hard);
		pan.add(resetLeft);
		pan.add(resetRight);
		pan.add(resetDown);
		pan.add(resetRotate);
		pan.add(resetHardDrop);
		pan.add(resetPause);
		pan.add(resetLeftButton);
		pan.add(resetRightButton);
		pan.add(resetDownButton);
		pan.add(resetRotateButton);
		pan.add(resetHardDropButton);
		pan.add(resetPauseButton);
		pan.add(resetScoreButton);
		pan.add(none);
		pan.add(colorBlindness);
        ButtonGroup groupBlindness = new ButtonGroup();
        groupBlindness.add(none);
        groupBlindness.add(colorBlindness);
		pan.add(resetSettingsButton);
		pan.add(confirm);
		
		add(pan);
		
		small.requestFocus();	
		
		ActionListener screenSizeActionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				// AbstractButton aButton = (AbstractButton) actionEvent.getSource();
				if (small.isSelected()) {
					Util.setScreenSize(Const.ScreenSize.SMALL);
				} else if (medium.isSelected()) {
					Util.setScreenSize(Const.ScreenSize.MEDIUM);
				} else if (large.isSelected()) {
					Util.setScreenSize(Const.ScreenSize.LARGE);
				}
			}
		};

		small.addActionListener(screenSizeActionListener);
		medium.addActionListener(screenSizeActionListener);
		large.addActionListener(screenSizeActionListener);

        // 난이도
		/*
		 * ActionListener gameModeActionListener = new ActionListener() { public void
		 * actionPerformed(ActionEvent actionEvent) { if(easy.isSelected()) {
		 * Util.setDifficulty(Difficulty.EASY); } else if (normal.isSelected()) {
		 * Util.setDifficulty(Difficulty.NORMAL); } else if (hard.isSelected()) {
		 * Util.setDifficulty(Difficulty.HARD); } } };
		 * 
		 * easy.addActionListener(gameModeActionListener);
		 * normal.addActionListener(gameModeActionListener);
		 * hard.addActionListener(gameModeActionListener);
		 */

		// Keyboard
		ActionListener keyboardActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (ae.getSource() instanceof JButton) {
					JDialog dialog = new JDialog();
					dialog.setModal(true);
					JLabel label = new JLabel("키를 입력하세요");
					dialog.add(label);
					dialog.setLocationRelativeTo(null);
					dialog.setSize(100, 100);
					dialog.setResizable(false);
					dialog.setFocusTraversalKeysEnabled(false);
					dialog.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosed(WindowEvent e) {
							refreshPref();
						}
					});
					dialog.addKeyListener(new KeyListener() {
						@Override
						public void keyTyped(KeyEvent e) {

						}

						@Override
						public void keyPressed(KeyEvent e) {
							System.out.println(e.getKeyCode());
							if (ae.getSource() == resetLeftButton)
								Util.setLeftBtnCode(e.getKeyCode());
							else if (ae.getSource() == resetRightButton)
								Util.setRightBtnCode(e.getKeyCode());
							else if (ae.getSource() == resetRotateButton)
								Util.setRotateRightBtnCode(e.getKeyCode());
							else if (ae.getSource() == resetPauseButton)
								Util.setPauseBtnCode(e.getKeyCode());
							else if (ae.getSource() == resetDownButton)
								Util.setSoftDropBtnCode(e.getKeyCode());
							else if (ae.getSource() == resetHardDropButton)
								Util.setHardDropBtnCode(e.getKeyCode());

							dialog.dispose();
						}

						@Override
						public void keyReleased(KeyEvent e) {

						}
					});
					dialog.setVisible(true);
				}
			}
		};

		resetLeftButton.addActionListener(keyboardActionListener);
		resetRightButton.addActionListener(keyboardActionListener);
		resetRotateButton.addActionListener(keyboardActionListener);
		resetPauseButton.addActionListener(keyboardActionListener);
		resetDownButton.addActionListener(keyboardActionListener);
		resetHardDropButton.addActionListener(keyboardActionListener);
		
        // Color Blindness
        ActionListener colorBlindnessActionListener = new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                if(none.isSelected()) {
                    Util.setColorBlindnessType(Const.ColorBlindnessType.NONE);
                } else if (colorBlindness.isSelected()) {
                    Util.setColorBlindnessType(Const.ColorBlindnessType.COLOR);
                }       
            }
        };

        none.addActionListener(colorBlindnessActionListener);
        colorBlindness.addActionListener(colorBlindnessActionListener);

        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
    			new MainMenu().setVisible(true);
    			dispose();
            }
        });
		resetSettingsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Util.clear();
				refreshPref();
			}
		});

		refreshPref();
	}

	private void refreshPref() {
		// Screen Size
		switch (Util.getScreenSize()) {
		case SMALL:
			small.setSelected(true);
			break;
		case MEDIUM:
			medium.setSelected(true);
			break;
		case LARGE:
			large.setSelected(true);
			break;
		}

		// Keyboard
		resetLeftButton.setText(KeyEvent.getKeyText(Util.getLeftBtnCode()));
		resetRightButton.setText(KeyEvent.getKeyText(Util.getRightBtnCode()));
		resetRotateButton.setText(KeyEvent.getKeyText(Util.getRotateRightBtnCode()));
		resetPauseButton.setText(KeyEvent.getKeyText(Util.getPauseBtnCode()));
		resetDownButton.setText(KeyEvent.getKeyText(Util.getSoftDropBtnCode()));
		resetHardDropButton.setText(KeyEvent.getKeyText(Util.getHardDropBtnCode()));

		// Color Blindness
		switch (Util.getColorBlindnessType()) {
		case NONE:
			none.setSelected(true);
			break;
		case COLOR:
			colorBlindness.setSelected(true);
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
		
}

