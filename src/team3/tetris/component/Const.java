package team3.tetris.component;

public class Const {

	public static final String APPLICATION_TITLE = "Tetris";

    public static final String PREF_SCREEN_SIZE_KEY = "SCREEN_SIZE";
    public static final String PREF_BTN_LEFT_KEY = "BTN_LEFT";
    public static final String PREF_BTN_RIGHT_KEY = "BTN_RIGHT";
    public static final String PREF_BTN_ROTATE_RIGHT_KEY = "BTN_ROTATE_RIGHT";
    public static final String PREF_BTN_PAUSE_KEY = "BTN_PAUSE";
    public static final String PREF_BTN_SOFT_DROP_KEY = "BTN_SOFT_DROP";
    public static final String PREF_BTN_EXIT_KEY = "BTN_EXIT";
    public static final String PREF_BTN_HARD_DROP_KEY = "BTN_HARD_DROP";
    public static final String PREF_COLOR_BLINDNESS_KEY = "COLOR_BLINDNESS";
    public static final String PREF_Difficulty_KEY = "Difficulty";
    
    public enum ScreenSize {
        SMALL, MEDIUM, LARGE;

        public static ScreenSize toEnum (String str) {
            try {
                return valueOf(str);
            } catch (Exception e) {
                return MEDIUM;
            }
        }
    }

    public enum ColorBlindnessType {
        NONE, COLOR;

        public static ColorBlindnessType toEnum(String str) {
            try {
                return valueOf(str);
            } catch (Exception e) {
                return NONE;
            }
        }
    }

    public static int HEIGHT = 20;
    public static int WIDTH = 10;
}