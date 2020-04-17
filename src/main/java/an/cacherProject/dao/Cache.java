package an.cacherProject.dao;

import java.util.Hashtable;
import an.cacherProject.model.Node;

public class Cache {

    private final int CAPACITY = 10; // feel free to change the capacity for the cache
    private Hashtable lookupTable;
    private Node start;
    private Node end;

    public Cache() {
        this.lookupTable = new Hashtable<String, Node>();
    }

    public int getCAPACITY() {
        return CAPACITY;
    }

    public String getValue(String key) {
        Node value = (Node) lookupTable.get(key);
        if(value == null) return null;

        removeNode(value);
        addOnTop(value);

        return value.getValue();
    }

    public boolean putValue(String key, String value) {
        // First check if value is already there
        if (lookupTable.containsKey(key)) {
            // Key exists! Update value
            Node currentNode = (Node) lookupTable.get(key);
            currentNode.setValue(value);

            // Now move the Node corresponding to the node to the top
            removeNode(currentNode);
            addOnTop(currentNode);
        } else {
            // If not, add the current value into our cache
            Node newValue = new Node(key, value);

            // Check if cache size had exceeded
            if (lookupTable.size() >= CAPACITY) {
                // remove last node and add this one in
                lookupTable.remove(this.end.getKey());
                removeNode(this.end);
                addOnTop(newValue);
            } else {
                addOnTop(newValue);
            }

            lookupTable.put(key, newValue);
        }

        return true;
    }

    private void addOnTop(Node node) {
        // adding the current node on top
        node.setRight(this.start);
        node.setLeft(null);

        // check if there is already a current start node
        if (this.start != null){
            this.start.setLeft(node);
        }

        // assigning the start node to the current node
        this.start = node;

        if (this.end == null) this.end = this.start;
    }

    private void removeNode(Node node) {
        Node currentNodeLeft = node.getLeft();
        Node currentNodeRight = node.getRight();

        if (currentNodeLeft != null) {
            currentNodeLeft.setRight(currentNodeRight);
        } else {
            this.start = currentNodeRight;
        }

        if (currentNodeRight != null) {
            currentNodeRight.setLeft(currentNodeLeft);
        } else {
            this.end = currentNodeLeft;
        }
    }
}