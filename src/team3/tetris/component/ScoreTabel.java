package team3.tetris.component;

import java.awt.Color;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import team3.tetris.record.Record;
import team3.tetris.record.RecordDTO;

/*
 * Scoreboard Frame에서 txt파일에 저장되어 있는 record를 테이블 형식으로 보여주는 JPanel
 */

class ScoreTable extends JPanel{
	JLabel scoreLabel;
	JTable scoreBoard;
	JTable new_scoreBoard;
	JLabel levelLabel;
	JLabel modeLabel;
	Record recordTable;
	String mode = "normal";
	String level = "esay";

	public ScoreTable(String mode, String level) throws IOException {
		this.mode = mode;
		this.level = level;

		setBackground(Color.BLACK);
		
		scoreLabel = new JLabel("Scoreboard");
		scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 17));
		scoreLabel.setForeground(Color.WHITE);

		modeLabel = new JLabel("MODE : "+mode);
		modeLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
		modeLabel.setForeground(Color.WHITE);

		levelLabel = new JLabel("LEVEL : "+level);
		levelLabel.setFont(new Font("SansSerif", Font.BOLD, 13));
		levelLabel.setForeground(Color.WHITE);
		// JTable

		//기존 scoreBoard 띄우기
		recordTable = new Record(mode, level);
		recordTable.fetchScoreBoard();
		//header가 안뜨는 오류가 있음
		String header[] = {"순위", "이름", "점수", "시간"};
		scoreBoard = new JTable(recordTable.toStringScoreBoard(), header);

		scoreBoard.setBackground(Color.BLACK);
		scoreBoard.setForeground(Color.WHITE);
		
		add(scoreLabel);
		add(modeLabel);
		add(levelLabel);
		add(scoreBoard);
	}
	public void updateScoreTable(RecordDTO newRecord) throws IOException {
		remove(scoreBoard);
		int rank = recordTable.setScoreBoard(newRecord);
		recordTable.setScoreBoardAtTxt();
		recordTable.fetchScoreBoard();
		new_scoreBoard = new JTable(recordTable.toStringScoreBoard(), new String[]{"순위", "이름", "점수", "시간"});
		new_scoreBoard.setBackground(Color.BLACK);
		new_scoreBoard.setForeground(Color.WHITE);

//		TableColumn col = new_scoreBoard.getColumnModel().getColumn(rank);
//		col.setCellRenderer(new MyRenderer(Color.BLACK, Color.YELLOW));

		add(new_scoreBoard);
	}
}
class MyRenderer extends DefaultTableCellRenderer {
	Color bg, fg;
	public MyRenderer(Color bg, Color fg) {
		super();
		this.bg = bg;
		this.fg = fg;
	}
}