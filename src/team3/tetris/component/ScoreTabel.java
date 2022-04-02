package team3.tetris.component;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import team3.tetris.record.Record;
import team3.tetris.record.RecordDTO;

/*
 * Scoreboard Frame에서 txt파일에 저장되어 있는 record를 테이블 형식으로 보여주는 JPanel
 */

class ScoreTable extends JPanel{
	JLabel scoreLabel;
	JTable scoreboard;
	
	RecordDTO recordDTO;
	Record record;
	
	public ScoreTable() throws IOException {
		setBackground(Color.BLACK);
		
		scoreLabel = new JLabel("Scoreboard");
		scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 15));
		scoreLabel.setForeground(Color.WHITE);
		
		// JTable
		String mode = "normal";
		String level = "esay";
		String header[] = {"순위", "이름", "점수", "시간"};

		recordDTO= new RecordDTO(mode, level, "KJW", 77, null);
		recordDTO.setTime(new Date());

		record = new Record("normal", "easy");
		record.fetchScoreBoard();
		record.setScoreBoard(recordDTO);
		
		scoreboard = new JTable(record.toStringScoreBoard(), header);
		scoreboard.setBackground(Color.BLACK);
		scoreboard.setForeground(Color.WHITE);
		
		add(scoreLabel);
		add(scoreboard);
	}
}