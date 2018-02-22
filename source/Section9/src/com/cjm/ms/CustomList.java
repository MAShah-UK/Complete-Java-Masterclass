package com.cjm.ms;

import java.util.*;

public class CustomList {
    private List<String> list = new LinkedList<>();

    public void add(String place) {
        list.add(place);
    }

    public void add(int index, String place) {
        list.add(index, place);
    }

    // Practice using Iterator.
    public void printList() {
        Iterator<String> iter = list.iterator();
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
        ListIterator<String> listIter = list.listIterator();

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

    public void selectElement() {

        ListIterator<String> iter = list.listIterator();

        if (list.isEmpty()) {
            System.out.println("No elements to select.");
            return;
        } else {
            System.out.println("Current element: " + iter.next());
            printOptions();
        }

        Scanner scan = new Scanner(System.in);
        boolean goingForward = true;
        boolean quit = false;
        while(!quit) {
            int action = scan.nextInt();
            scan.nextLine();
            switch (action) {
                case 0:
                    System.out.println("End of list.");
                    quit = true;
                    break;
                case 1:
                    if (!goingForward && iter.hasNext()) {
                        iter.next();
                        goingForward = true;
                    }

                    if (iter.hasNext()) {
                        System.out.println("Current element: " + iter.next());
                    } else {
                        System.out.println("You're already at the last element.");
                    }
                    break;
                case 2:
                    if (goingForward && iter.hasPrevious()) {
                        iter.previous();
                        goingForward = false;
                    }

                    if (iter.hasPrevious()) {
                        System.out.println("Current element: " + iter.previous());
                    } else {
                        System.out.println("You're already at the first element.");
                    }
                    break;
                case 3:
                    printOptions();
                    break;
            }
        }
    }

    private static void printOptions() {
        System.out.println("Enter a value to select the corresponding option:");
        System.out.println("0 - To quit.");
        System.out.println("1 - To see the next element.");
        System.out.println("2 - To see the previous element.");
    }
}
