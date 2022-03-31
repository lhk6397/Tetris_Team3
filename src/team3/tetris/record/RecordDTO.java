package team3.tetris.record;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordDTO {
    private String mode;
    private String level;
    private String name;
    private int score;
    private String time;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");

    //DTO 생성자
    public RecordDTO(String mode, String level, String name, int score, String time) {
        this.mode = mode;
        this.level = level;
        this.name = name;
        this.score = score;
        this.time = time;
    }


    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(Date date) {
        this.time = simpleDateFormat.format(date);
    }
}
