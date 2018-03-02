package com.cjm.ms;

public abstract class ListItem {
    private ListItem previous;
    private ListItem next;
    private String value;

    public abstract void setPrevious();
    public abstract void setNext();

    public abstract void previousItem();
    public abstract void nextItem();

    public int compareTo(ListItem item) {
        return value.compareTo(item.value);
    }
}
