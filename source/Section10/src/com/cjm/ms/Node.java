package com.cjm.ms;

public class Node extends ListItem {

    public Node(String value) {
        super(value);
    }

    @Override
    public ListItem getPrevious() {
        return previous;
    }

    @Override
    public ListItem getNext() {
        return next;
    }

    @Override
    public void setPrevious(ListItem item) {
        previous = item;
    }

    @Override
    public void setNext(ListItem item) {
        next = item;
    }
}
