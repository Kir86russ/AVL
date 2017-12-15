package avl_tree;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import static avl_tree.Constants.*;

class ControlField extends Pane { //панель, на которой расположены элементы управления (кнопки, текстовые поля)
    private TextField textField = new TextField();
    private Label message = new Label();

    ControlField() {
        addTextField();
        addButtonAdd();
        addButtonRemove();
        addButtonClear();
        addMessage();
    }

    private void addTextField() { //создаем поле ввода
        textField.setPrefSize(TEXTFIELD_WIDTH, BUTTON_AND_TEXTFIELD_HEIGHT);
        textField.setTranslateX(TEXTFIELD_X);
        textField.setTranslateY(BUTTON_AND_TEXTFIELD_Y);
        getChildren().add(textField);
    }

    private void addButtonAdd() { //создаем кнопку add
        Button buttonAdd = new Button();
        buttonAdd.setText("Add");
        buttonAdd.setPrefSize(BUTTON_WIDTH, BUTTON_AND_TEXTFIELD_HEIGHT);
        buttonAdd.setTranslateX(TEXTFIELD_X + TEXTFIELD_WIDTH + 10);
        buttonAdd.setTranslateY(BUTTON_AND_TEXTFIELD_Y);
        buttonAdd.setOnAction(event -> {
            if (isDouble(textField.getText())) { //проверяем, является ли ввёденное в поле ввода числом
                Double element = Double.parseDouble(textField.getText());
                if (Main.tree.contains(element)) { //если дерево уже содержит такой элемент, выводим сообщение об ошибке
                    message.setTextFill(ERROR_MESSAGE_COLOR);
                    message.setText("Element " + element + " already exists");
                } else { //если такого элемента в дереве нет, добавляем элемент в дерево, перерисовываем дерево
                    Main.tree.add(element);
                    message.setTextFill(SUCCESS_MESSAGE_COLOR);
                    message.setText("Element " + element + " was added");
                    Main.treeField.getChildren().clear();
                    Main.treeField.drawTree(Main.tree.getRoot(), WINDOW_WIDTH / 2 - VERTEX_RADIUS, TREE_Y, 240);
                }
            } else if (!textField.getText().isEmpty()) { //если введено не число, выводим сообщение об ошибке
                message.setTextFill(ERROR_MESSAGE_COLOR);
                message.setText("Incorrect format");
            }
            textField.clear();
        });
        getChildren().add(buttonAdd);
    }

    private void addButtonRemove() { //создаем кнопку remove
        Button buttonRemove = new Button();
        buttonRemove.setText("Remove");
        buttonRemove.setPrefSize(BUTTON_WIDTH, BUTTON_AND_TEXTFIELD_HEIGHT);
        buttonRemove.setTranslateX(TEXTFIELD_X + TEXTFIELD_WIDTH + BUTTON_WIDTH + 20);
        buttonRemove.setTranslateY(BUTTON_AND_TEXTFIELD_Y);
        buttonRemove.setOnAction(event -> {
            if (isDouble(textField.getText())) { //проверяем, является ли введенное в поле ввода числом
                Double element = Double.parseDouble(textField.getText());
                if (Main.tree.contains(element)) { //если дерево содержит такой элемент, удаляем его, перерисовываем дерево
                    Main.tree.remove(element);
                    message.setTextFill(SUCCESS_MESSAGE_COLOR);
                    message.setText("Element " + element + " was removed");
                    Main.treeField.getChildren().clear();
                    Main.treeField.drawTree(Main.tree.getRoot(), WINDOW_WIDTH / 2 - VERTEX_RADIUS, TREE_Y, 240);
                } else { //если такого элемента в дереве нет, выводим сообщение об ошибке
                    message.setTextFill(ERROR_MESSAGE_COLOR);
                    message.setText("There is no such element");
                }
            } else if (!textField.getText().isEmpty()) { //если введено не число, выводим сообщение об ошибке
                message.setTextFill(ERROR_MESSAGE_COLOR);
                message.setText("Incorrect format");
            }
            textField.clear();
        });
        getChildren().add(buttonRemove);
    }

    private void addButtonClear() { //создаем кнопку clear
        Button buttonClear = new Button();
        buttonClear.setText("Clear");
        buttonClear.setPrefSize(BUTTON_WIDTH, BUTTON_AND_TEXTFIELD_HEIGHT);
        buttonClear.setTranslateX(WINDOW_WIDTH - BUTTON_WIDTH - 10);
        buttonClear.setTranslateY(BUTTON_AND_TEXTFIELD_Y);
        buttonClear.setOnAction(event -> { //очищаем поле, на котором строится дерево и создаем новое пустое дерево
            Main.tree = new TreeAVL();
            Main.treeField.getChildren().clear();
            message.setTextFill(SUCCESS_MESSAGE_COLOR);
            message.setText("Tree was cleared");
        });
        getChildren().add(buttonClear);
    }

    private void addMessage() { //создаем поле для сообщений об успешном/неуспешном выполнении операции
        message.setTranslateX(TEXTFIELD_X + TEXTFIELD_WIDTH + BUTTON_WIDTH * 2 + 50);
        message.setTranslateY(BUTTON_AND_TEXTFIELD_Y + 5);
        message.setFont(LARGE_FONT);
        getChildren().add(message);
    }

    private boolean isDouble(String str) { //проверяем, является ли строка числом
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
