package team3.tetris.record;

import java.io.*;
import java.util.ArrayList;


public class Record {
    private String mode;
    private String difficulty;
    private ArrayList<RecordDTO> scoreBoard = new ArrayList<RecordDTO>();
    private int changedRank;
    private String fileURL;

    
    public Record(String mode, String difficulty) throws IOException {
        this.mode = mode;
        this.difficulty = difficulty;
        this.fileURL = "../Tetris_Team3/records/" + mode + "/" + difficulty + ".txt";
    }
    //txt 기존 저장되어있던 레코드를 scoreBoard 넣는 함수
    public ArrayList<RecordDTO> fetchScoreBoard() throws IOException {
        //스코어 보드 가져오기
        BufferedReader reader = new BufferedReader(new FileReader(fileURL));
        String str;
        String[] line;
        while ((str = reader.readLine()) != null) {
            line = str.split(" ");
            RecordDTO records = new RecordDTO(mode, difficulty, line[0], Integer.parseInt(line[1]), line[2]);
            scoreBoard.add(records);
        }
        return scoreBoard;
    }

    //새 레코드가 들어왔을 때 스코어보드에 기록하는 함수
    public int setScoreBoard(RecordDTO new_record) {
        int new_recordScore = new_record.getScore();
        if(scoreBoard.size() == 0) {
        	scoreBoard.add(0, new_record);
        	changedRank = 0;
        	return changedRank;
        }
        for (int i = 0; i < scoreBoard.size(); i++) {
            if (scoreBoard.get(i).getScore() <= new_recordScore) {
                scoreBoard.add(i, new_record);
                changedRank = i;
                break;
            }
        }
        return changedRank;
    }

    //setScoreBoard 이후 txt파일에 rewrite하는 함수
    public void setScoreBoardAtTxt() {
        String str;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(fileURL, false));
            for (RecordDTO record : scoreBoard) {
                str = record.getName() + " " + record.getScore() + " " + record.getTime();
                bw.write(str + System.getProperty("line.separator"));
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //2차원 string 배열로 반환하는 함수
    public String[][] toStringScoreBoard() {
        String [][] table = new String[scoreBoard.size()][4];
        for (int i = 0; i<scoreBoard.size(); i++) {
            RecordDTO record = scoreBoard.get(i);
            table[i][0] = Integer.toString(i+1);
            table[i][1] = record.getName();
            table[i][2] = Integer.toString(record.getScore());
            table[i][3] = record.getTime();
        }
        return table;
    }
    public void clearScoreBoard() {
        scoreBoard.clear();
    }
}