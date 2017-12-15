package avl_tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

class TreeAVL<T extends Comparable<T>> {
    class Node<T> {
        @Override
        public String toString() {
            return String.valueOf(key);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node<?> node = (Node<?>) o;

            return key != null ? key.equals(node.key) : node.key == null;

        }

        T key;
        int height = 0;
        int balance = 0;
        Node<T> left, right;
        Node<T> parent;

        Node(T key, Node<T> parent) {
            this.key = key;
            this.parent = parent;
        }

        T getKey() {
            return key;
        }

        Node<T> getLeft() {
            return left;
        }

        Node<T> getRight() {
            return right;
        }
    }

    public Printer printer = new Printer();

    private Node<T> root = null;
    ArrayList<Node> NodesList = new ArrayList<>();

    Node<T> getRoot() {
        return root;
    }

    public boolean add(T t) {
        Node<T> closest = find(t);
        int comparison;
        if (closest == null) comparison = -1;
        else comparison = t.compareTo(closest.key);
        if (comparison == 0) {
            return false;
        }

        Node<T> newNode;

        if (closest == null) {
            newNode = new Node<>(t, null);
            root = newNode;
            NodesList.add(newNode);

        } else if (comparison < 0) {
            if (closest.left == null) {
                newNode = new Node<>(t, closest);
                NodesList.add(newNode);
                closest.left = newNode;
            }
        } else {
            if (closest.right == null) {
                newNode = new Node<>(t, closest);
                NodesList.add(newNode);
                closest.right = newNode;
            }
        }
        if (closest != null) rebalance(closest);

        return true;
    }

    private Node<T> find(T key) {
        if (root == null) return null;
        return find(root, key);
    }

    private Node<T> find(Node<T> start, T key) {
        int comparison = key.compareTo(start.key);
        if (comparison == 0) {
            return start;
        } else if (comparison < 0) {
            if (start.left == null) return start;
            return find(start.left, key);
        } else {
            if (start.right == null) return start;
            return find(start.right, key);
        }
    }

    private void rebalance(Node n) {
        doingBalance(n);

        if (n.balance == -2) {
            if (height(n.left.left) >= height(n.left.right))
                n = rotateRight(n);
            else
                n = rotateLeftRight(n);

        } else if (n.balance == 2) {
            if (height(n.right.right) >= height(n.right.left))
                n = rotateLeft(n);
            else
                n = rotateRightLeft(n);
        }

        if (n.parent != null) {
            rebalance(n.parent);
        } else {
            root = n;
        }
    }

    private void doingBalance(Node... nodes) {
        for (Node n : nodes) {
            reheight(n);
            n.balance = height(n.right) - height(n.left);
        }
    }

    private Node rotateLeft(Node a) {

        Node b = a.right;
        b.parent = a.parent;

        a.right = b.left;

        if (a.right != null)
            a.right.parent = a;

        b.left = a;
        a.parent = b;

        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }

        doingBalance(a, b);

        return b;
    }

    private Node rotateRight(Node a) {

        Node b = a.left;
        b.parent = a.parent;

        a.left = b.right;

        if (a.left != null)
            a.left.parent = a;

        b.right = a;
        a.parent = b;

        if (b.parent != null) {
            if (b.parent.right == a) {
                b.parent.right = b;
            } else {
                b.parent.left = b;
            }
        }

        doingBalance(a, b);

        return b;
    }


    private Node rotateLeftRight(Node n) {
        n.left = rotateLeft(n.left);
        return rotateRight(n);
    }

    private Node rotateRightLeft(Node n) {
        n.right = rotateRight(n.right);
        return rotateLeft(n);
    }

    private int height(Node n) {
        if (n == null) return -1;
        return n.height;
    }


    private void reheight(Node node) {
        node.height = Math.max(height(node.left), height(node.right)) + 1;
    }

    public boolean remove(T delKey) {
        if (root == null) return false;

        Node<T> node = find(delKey);
        delete(node);
        NodesList.remove(node);
        return true;
    }

    private void delete(Node node) {
        if (node.left == null && node.right == null) {
            if (node.parent == null) {
                root = null;
            } else {
                Node parent = node.parent;
                if (parent.left == node) {
                    parent.left = null;
                } else {
                    parent.right = null;
                }
                rebalance(parent);
            }
            return;
        }

        if (node.left != null) {
            Node child = node.left;
            while (child.right != null) child = child.right;
            node.key = child.key;
            delete(child);
        } else {
            Node child = node.right;
            while (child.left != null) child = child.left;
            node.key = child.key;
            delete(child);
        }
    }

    public boolean contains(T o) {
        T t = o;
        Node<T> closest = find(t);
        return closest != null && t.compareTo(closest.key) == 0;
    }

    public class Printer {
        private Queue el = new ArrayDeque();
        private int count = 0;
        private int perenos = 2;

        public void print(Node node) {
            if (node.parent == null)
                System.out.println(node);
            System.out.print(node.left + "-");
            System.out.print(node.right + "   ");
            count += 2;
            if (node.left != null)
                el.add(node.left);
            if (node.right != null)
                el.add(node.right);
            if (el.isEmpty()) {
                return;
            }
            if (count == perenos) {
                System.out.println();
                perenos += 2;
                count = 0;
            }
            print((Node) el.poll());
        }
    }
}
