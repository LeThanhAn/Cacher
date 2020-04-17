package an.cacherProject.model;

public class Node {
    private String key;
    private String value;
    private Node right;
    private Node left;

    public Node(String key, String value) {
        this.key = key;
        this.value = value;
        this.right = null;
        this.left = null;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Node getRight() {
        return right;
    }

    public Node getLeft() {
        return left;
    }
}
