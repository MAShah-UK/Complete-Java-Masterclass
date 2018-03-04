package com.cjm.ms;

public class DoublyLinkedList {
    private ListItem root;

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

    private boolean addSingle(String value) {
        boolean done = false;
        ListItem newItem = new Node(value);

        if (root == null) {
            root = newItem;
            return done;
        }

        ListItem curr = root;
        while(!done) {
            int comp = newItem.compareTo(curr);
            if (comp < 0) { // Put newItem between links.
                newItem.setPrevious(curr.getPrevious());
                newItem.setNext(curr);
                curr.setPrevious(newItem);
                done = true;
            } else if (comp > 0) {
                if (curr.getNext() != null) { // Move to next link.
                    curr = curr.getNext();
                } else { // Put newItem as last link.
                    curr.setNext(newItem);
                    newItem.setPrevious(curr);
                    done = true;
                }
            } else { // comp = 0
                // Do nothing. Duplicates not allowed.
                done = true;
            }
        }

        return done;
    }

    private ListItem findElement(int index) {
        ListItem curr = root;
        if (curr == null) {
            return null;
        }
        for (int i = 0; i < index; i++) {
            if (curr.getNext() == null) {
                return null;
            }
            curr = curr.getNext();
        }
        return curr;
    }

    public String get(int index) {
        ListItem item = findElement(index);
        return (item == null) ? null : item.getValue();
    }

    public boolean remove(int index) {
        ListItem item = findElement(index);
        boolean isValid = (item != null);
        if (isValid) {
            ListItem prev = item.getPrevious();
            ListItem next = item.getNext();
            if (prev != null) {
                prev.setNext(next);
            }
            if (next != null) {
                next.setPrevious(prev);
            }
        }
        return isValid;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        ListItem curr = root;
        if (curr == null) {
            return null;
        }
        while (curr.getNext() != null) {
            output.append(curr.getValue());
            curr = curr.getNext();
        }

        return output.toString();
    }
}
