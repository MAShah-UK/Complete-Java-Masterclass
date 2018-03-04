package com.cjm.ms;

public class DoublyLinkedList {
    private ListItem root;

    prviate void connectLinks(ListItem prev, ListItem next) {
        prev.setNext(next);
        next.setPrevious(prev);
    }

    private void connectLinks(ListItem prev, ListItem middle, ListItem next) {
        connectLinks(prev, middle);
        connectLinks(middle, next);
    }

    private boolean addSingle(String value) {
        ListItem newItem = new Node(value);

        if (root == null) {
            root = newItem;
            return true;
        }

        boolean done = false;
        ListItem curr = root;
        int prevComp = newItem.compareTo(curr);
        while(!done) {
            System.out.println(curr.getValue());
            int comp = newItem.compareTo(curr);
            if

            if (comp < 0) { // Compare previous links.
                if (curr.getPrevious() != null) { // Move to previous link.
                    curr = curr.getPrevious();
                } else { // Put newItem as first link.
                    root = newItem;
                    connectLinks(newItem, curr);
                    done = true;
                }
            } else if (comp > 0) { // Compare next links.
                if (curr.getNext() != null) { // Move to next link.
                    curr = curr.getNext();
                } else { // Put newItem as last link.
                    connectLinks(curr, newItem);
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
