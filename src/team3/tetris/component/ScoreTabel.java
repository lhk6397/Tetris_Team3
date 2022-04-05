package team3.tetris.component;

import java.awt.*;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

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
		recordTable.clearScoreBoard();
		recordTable.fetchScoreBoard();
		String header[] = {"순위", "이름", "점수", "시간"};
		new_scoreBoard = new JTable(recordTable.toStringScoreBoard(), header) {
			public Component prepareRenderer(TableCellRenderer renderer,
											 int row, int column)
			{
				Component c = super.prepareRenderer(renderer, row, column);
				Color color1 = Color.YELLOW;
				Color color2 = Color.WHITE;
				if(!c.getBackground().equals(getSelectionBackground())) {
					Color coleur = (row == rank ? color1 : color2);
					c.setBackground(Color.BLACK);
					c.setForeground(coleur);
					coleur = null;
				}
				return c;
			}
		};
		add(new_scoreBoard);



//		add(new_scoreBoard);
	}
}


