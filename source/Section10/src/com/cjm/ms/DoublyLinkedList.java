package com.cjm.ms;

public class DoublyLinkedList {
    private ListItem root;

    public void add(String value) {
        ListItem newItem = new Node(value);

        if (root == null) {
            root = newItem;
            return;
        }

        ListItem curr = root;
        boolean added = false;
        while(!added) {
            // Add before end of list.
            int comp = newItem.compareTo(curr);
            if (comp < 0) {
                newItem.setPrevious(curr.getPrevious());
                newItem.setNext(curr);
                curr.setPrevious(newItem);
                added = true;
            } else if (comp > 0) {
                // Traverse links.
                if (curr.getNext() != null) {
                    curr = curr.getNext();
                } else { // If next item is null, newItem goes at end of list.
                    curr.setNext(newItem);
                    curr.getNext().setPrevious(curr);
                    added = true;
                }
            } else { // comp == 0

                added = true;
            }
        }
    }

    public void get(int index) {

    }

    public void remove(int index) {

    }
}
