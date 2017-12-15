package avl_tree;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static avl_tree.Constants.*;

public class Main extends Application {
    static Pane mainField = new Pane();
    static TreeField treeField = new TreeField();
    static ControlField controlField = new ControlField();
    static TreeAVL tree = new TreeAVL();

    @Override
    public void start(Stage stage){
        stage.setResizable(false);
        stage.setTitle("AVL Tree");
        mainField.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        mainField.setStyle("-fx-background-color: #2d2d2d");
        mainField.getChildren().addAll(treeField, controlField);
        stage.setScene(new Scene(mainField));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
