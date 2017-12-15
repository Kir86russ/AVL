package avl_tree;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import static avl_tree.Constants.*;

class ControlField extends Pane {
    private TextField textField = new TextField();
    private Label message = new Label();

    ControlField() {
        addTextField();
        addButtonAdd();
        addButtonRemove();
        addButtonClear();
        addMessage();
    }

    private void addTextField() {
        textField.setPrefSize(TEXTFIELD_WIDTH, BUTTON_AND_TEXTFIELD_HEIGHT);
        textField.setTranslateX(TEXTFIELD_X);
        textField.setTranslateY(BUTTON_AND_TEXTFIELD_Y);
        getChildren().add(textField);
    }

    private void addButtonAdd() {
        Button buttonAdd = new Button();
        buttonAdd.setText("Add");
        buttonAdd.setPrefSize(BUTTON_WIDTH, BUTTON_AND_TEXTFIELD_HEIGHT);
        buttonAdd.setTranslateX(TEXTFIELD_X + TEXTFIELD_WIDTH + 10);
        buttonAdd.setTranslateY(BUTTON_AND_TEXTFIELD_Y);
        buttonAdd.setOnAction(event -> {
            if (isNumber(textField.getText())) {
                Integer element = Integer.parseInt(textField.getText());
                if (Main.tree.contains(element)) {
                    message.setTextFill(ERROR_MESSAGE_COLOR);
                    message.setText("Элемент " + element + " уже есть");
                } else {
                    Main.tree.add(element);
                    message.setTextFill(SUCCESS_MESSAGE_COLOR);
                    message.setText("Элемент " + element + " был добавлен");
                    Main.treeField.getChildren().clear();
                    Main.treeField.drawTree(Main.tree.getRoot(), WINDOW_WIDTH / 2, TREE_Y, 240);
                }
            } else if (!textField.getText().isEmpty()) {
                message.setTextFill(ERROR_MESSAGE_COLOR);
                message.setText("Некорректный формат");
            }
            textField.clear();
        });
        getChildren().add(buttonAdd);
    }

    private void addButtonRemove() {
        Button buttonRemove = new Button();
        buttonRemove.setText("Remove");
        buttonRemove.setPrefSize(BUTTON_WIDTH, BUTTON_AND_TEXTFIELD_HEIGHT);
        buttonRemove.setTranslateX(TEXTFIELD_X + TEXTFIELD_WIDTH + BUTTON_WIDTH + 20);
        buttonRemove.setTranslateY(BUTTON_AND_TEXTFIELD_Y);
        buttonRemove.setOnAction(event -> {
            if (isNumber(textField.getText())) {
                Integer element = Integer.parseInt(textField.getText());
                if (Main.tree.contains(element)) {
                    Main.tree.remove(element);
                    message.setTextFill(SUCCESS_MESSAGE_COLOR);
                    message.setText("Элемент " + element + " был удалён");
                    Main.treeField.getChildren().clear();
                    Main.treeField.drawTree(Main.tree.getRoot(), WINDOW_WIDTH / 2, TREE_Y, 240);
                } else {
                    message.setTextFill(ERROR_MESSAGE_COLOR);
                    message.setText("Такого элемента нет");
                }
            } else if (!textField.getText().isEmpty()) {
                message.setTextFill(ERROR_MESSAGE_COLOR);
                message.setText("Некорректный формат");
            }
            textField.clear();
        });
        getChildren().add(buttonRemove);
    }

    private void addButtonClear() {
        Button buttonClear = new Button();
        buttonClear.setText("Clear");
        buttonClear.setPrefSize(BUTTON_WIDTH, BUTTON_AND_TEXTFIELD_HEIGHT);
        buttonClear.setTranslateX(WINDOW_WIDTH - BUTTON_WIDTH - 10);
        buttonClear.setTranslateY(BUTTON_AND_TEXTFIELD_Y);
        buttonClear.setOnAction(event -> {
            Main.tree = new TreeAVL();
            Main.treeField.getChildren().clear();
            message.setTextFill(SUCCESS_MESSAGE_COLOR);
            message.setText("Дерево было очищено");
        });
        getChildren().add(buttonClear);
    }

    private void addMessage() {
        message.setTranslateX(TEXTFIELD_X + TEXTFIELD_WIDTH + BUTTON_WIDTH * 2 + 50);
        message.setTranslateY(BUTTON_AND_TEXTFIELD_Y + 5);
        message.setFont(LARGE_FONT);
        getChildren().add(message);
    }

    private boolean isNumber(String str) {
        try {
           Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
