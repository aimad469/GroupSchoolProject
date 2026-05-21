package org.example.model;

import java.util.ArrayList;

public class EmployeeTree {
    private Node root;

    public EmployeeTree() {
        this.root = null;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }


    public ArrayList<ArrayList<Node>> levelOrder(Node root) {
        // Stores the result level by level
        ArrayList<ArrayList<Node>> res = new ArrayList<>();
        levelOrderRec(root, 0, res);
        return res;
    }

    private void levelOrderRec(Node root, int level, ArrayList<ArrayList<Node>> res) {
        // Base case
        if (root == null)
            return;

        // Add a new level to the result if needed
        if (res.size() <= level) {
            res.add(new ArrayList<>());
        }

        // Add current node to its corresponding level
        res.get(level).add(root);

        // Recur for left and right children
        levelOrderRec(root.getLeft(), level + 1, res);
        levelOrderRec(root.getRight(), level + 1, res);
    }
}