package com.cjm.ms;

public class BinarySearchTree {
    ListItem root;

    private boolean addSingle(String value) {
        ListItem newItem = new Node(value);

        if (root == null) {
            root = newItem;
            return true;
        }

        boolean done = false;
        ListItem curr = root;
        while(!done) {
            int comp = newItem.compareTo(curr);
            if (comp < 0) { // Compare left link.
                if (curr.getPrevious() != null) {
                    curr = curr.getPrevious(); // Move to left link.
                } else { // Insert newItem as left link.
                    curr.setPrevious(newItem);
                    done = true;
                }
            } else if (comp > 0) { // Compare right link.
                if (curr.getNext() != null) {
                    curr = curr.getNext(); // Move to right link.
                } else { // Insert newItem as right link.
                    curr.setNext(newItem);
                    done = true;
                }
            } else { // comp = 0
                // Do nothing. Duplicates not allowed.
                done = true;
            }
        }
        return done;
    }

    public boolean add(String data) {
        if (data == null) {
            return false;
        }

        boolean done = true;
        String[] values = data.split(" ");
        for (String newValue : values) {
            boolean added = addSingle(newValue);
            if (!added) {
                done = false;
            }
        }

        return done;
    }
}
