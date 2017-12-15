package avl_tree;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

import static avl_tree.Constants.*;

class TreeField<T> extends Pane { //поле, на котором строится дерево
    class Vertex<T> extends BorderPane { //изображение узла дерева
        private T value;

        Vertex(T value) {
            this.value = value;
            setPrefSize(2 * VERTEX_RADIUS, 2 * VERTEX_RADIUS);
            addCircle();
            addText();
        }

        private void addCircle() {
            Circle circle = new Circle(VERTEX_RADIUS);
            circle.setFill(TREE_COLOR);
            circle.setCenterX(VERTEX_RADIUS);
            circle.setCenterY(VERTEX_RADIUS);
            getChildren().add(circle);
        }

        private void addText() {
            Label text = new Label();
            String strValue = String.valueOf(value);
            text.setText(strValue);
            text.setTextFill(TEXT_COLOR);
            if (strValue.length() <= 5) text.setFont(LARGE_FONT);
            else if (strValue.length() <= 7) text.setFont(MEDIUM_FONT);
            else text.setFont(SMALL_FONT);
            setCenter(text);
        }
    }

    void drawTree(TreeAVL.Node node, double x, double y, double k) { //метод, отрисовывающий дерево. работает рекурсивно
        if (node != null) {
            addVertex((T) node.getKey(), x, y);
            if (node.getLeft() != null) {
                addLine(x + VERTEX_RADIUS, y + VERTEX_RADIUS,
                        x + VERTEX_RADIUS - k, y + VERTEX_RADIUS + 60);
                addVertex((T) node.getKey(), x, y);
                drawTree(node.getLeft(), x - k, y + 60, k - k / 2);
            }
            if (node.getRight() != null) {
                addLine(x + VERTEX_RADIUS, y + VERTEX_RADIUS,
                        x + VERTEX_RADIUS + k, y + VERTEX_RADIUS + 60);
                addVertex((T) node.getKey(), x, y);
                drawTree(node.getRight(), x + k, y + 60, k - k / 2);
            }
        }
    }

    private void addVertex(T value, double x, double y) { //метод для добавления вершин
        Vertex vertex = new Vertex(value);
        vertex.setTranslateX(x);
        vertex.setTranslateY(y);
        getChildren().add(vertex);
    }

    private void addLine(double startX, double startY, double endX, double endY) { //метод для добавления ребер между вершинами
        Line line = new Line(startX, startY, endX, endY);
        line.setStroke(TREE_COLOR);
        line.setStrokeWidth(3);
        getChildren().add(line);
    }
}
