package com.cjm.ms;

public class DoublyLinkedList {
    private ListItem root;

    private void connectLinks(ListItem prev, ListItem next) {
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
            int comp = newItem.compareTo(curr);
            // If sorting sign changed.
            if (comp * prevComp < 0) { // Put newItem between links.
                if (comp > 0) {
                    connectLinks(curr, newItem, curr.getNext());
                } else { // comp < prevComp
                    connectLinks(curr.getPrevious(), newItem, curr);
                }
                done = true;
            } else if (comp < 0) { // Compare previous links.
                if (curr.getPrevious() != null) {
                    curr = curr.getPrevious(); // Move to previous link.
                } else { // Put newItem as first link.
                    connectLinks(newItem, curr);
                    root = newItem;
                    done = true;
                }
            } else if (comp > 0) { // Compare next links.
                if (curr.getNext() != null) {
                    curr = curr.getNext(); // Move to next link.
                } else { // Put newItem as last link.
                    connectLinks(curr, newItem);
                    done = true;
                }
            } else { // comp = 0
                // Do nothing. Duplicates not allowed.
                done = true;
            }
            prevComp = comp;
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
        while (curr != null) {
            output.append(curr.getValue());
            output.append(" ");
            curr = curr.getNext();
        }

        return output.toString();
    }
}
