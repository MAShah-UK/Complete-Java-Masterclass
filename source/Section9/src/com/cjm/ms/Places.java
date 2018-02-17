package com.cjm.ms;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Places {
    private List<String> places = new LinkedList<>();

    public void add(String place) {
        places.add(place);
    }

    public void add(int index, String place) {
        places.add(index, place);
    }

    // Practice using Iterator.
    public void printList() {
        Iterator<String> iter = places.iterator();
        // hasNext() checks to see if next element exists.
        while(iter.hasNext()) {
            // next() returns the next element and moves the iterator along.
            System.out.println("Visiting: " + iter.next());
        }
    }

    // Practice using ListIterator.
    // Adds new element in alphabetical order.
    public boolean addInOrder(String newElem) {
        boolean validEntry = true;
        boolean done = false;
        // ListIterator works like Iterator, but has more functionality.
        ListIterator<String> listIter = places.listIterator();


        while(listIter.hasNext() && !done) {
            int comparison = listIter.next().compareTo(newElem);
            if (comparison == 0) {
                // Element already exists, exit without adding.
                System.out.println(newElem + " already exists.");
                validEntry = false;
                done = true;
            } else if (comparison > 0) {
                // newElem is lexicographically > currElem.
                listIter.previous();
                listIter.add(newElem);
                done = true;
            } else { // comparison < 0.
                // Go to the next loop iteration.
            }
        }

        // If the while loop runs out of elements before adding
        // newElem, then it must be added to the end of the list.
        if (!done) {
            listIter.add(newElem);
        }

        return validEntry;
    }
}
