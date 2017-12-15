package avl_tree;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

class Constants { //класс для хранения констант, использующихся в других классах
    static final double WINDOW_WIDTH = 1000;
    static final double WINDOW_HEIGHT = 600;
    static final double BUTTON_WIDTH = 80;
    static final double BUTTON_AND_TEXTFIELD_HEIGHT = 30;
    static final double TEXTFIELD_WIDTH = BUTTON_WIDTH * 1.5;
    static final double TEXTFIELD_X = 20;
    static final double BUTTON_AND_TEXTFIELD_Y = 20;
    static final double TREE_Y = 80;
    static final double VERTEX_RADIUS = 24;

    static final Color SUCCESS_MESSAGE_COLOR = Color.PALEGREEN;
    static final Color ERROR_MESSAGE_COLOR = Color.valueOf("#FF6168");
    static final Color TEXT_COLOR = Color.valueOf("#2d2d2d");
    static final Color TREE_COLOR = Color.AQUAMARINE;

    static final Font LARGE_FONT = new Font(16);
    static final Font MEDIUM_FONT = new Font(12);
    static final Font SMALL_FONT = new Font(9);
}
