package com.cjm.ms;

// Exercise:
// Create a program that implements a simple mobile phone with the following capabilities.
// Able to store, modify, remove and query contact names.
// You will want to create a separate class for Contact (name and phone number).
// Create a master class (MobilePhone) that holds the ArrayList of Contact.
// The MobilePhone class has the functionality listed above.
// Add a menu of options that are available.
// Options:  Quit, print list of contacts, add new contact, update existing contact, remove contact
// and search/find contact.
// When adding or updating be sure to check if the contact already exists (use name).
// Be sure not to expose the inner workings of the ArrayList to MobilePhone
// e.g. no ints, no .get(i), etc.
// MobilePhone should do everything with Contact objects only.

import java.util.ArrayList;
import java.util.List;

public class MobilePhone {
    private List<Contact> contacts = new ArrayList<>();

    private int findContact(String name) {
        // Probably more efficient to use ArrayList.contains().
        int index = -1;
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equals(name)) {
                index = i;
            }
        }
        return index;
    }

    public void addContact(String name, String number) {
        if (findContact(name) == -1) { // Index -1 means contact not found.
            Contact cont = new Contact(name, number);
            contacts.add(cont);
            System.out.println("New contact " + name + " was added with number " + number +".");
        } else {
            System.out.println("A contact with the same name already exists.");
        }
    }

    public void modifyContact(String name, String newNumber) {
        int index = findContact(name);
        if (index > -1) {
            Contact contact = contacts.get(index);
            String oldNumber = contact.getNumber();
            contact.setContact(name, newNumber);
            System.out.println(name + "'s old number " + oldNumber + " has been replaced with " + newNumber + ".");
        } else {
            System.out.println("The contact does not exist.");
        }
    }

    public void removeContact(String name) {
        int index = findContact(name);
        if (index > -1) {
            contacts.remove(index);
            System.out.println("Contact details for " + name + " were deleted.");
        } else {
            System.out.println("The contact does not exist");
        }
    }

    public void listContacts() {
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            String name = contact.getName();
            String number = contact.getNumber();
            System.out.print(i+1 + ") " + name + " " + number + "\n");
        }
    }

    public void findNumber(String name) {
        int index = findContact(name);
        String number = contacts.get(index).getNumber();
        System.out.println(name + "'s number is: " + number);
    }

    public void printInstructions() {
        System.out.println("\nPress");
        System.out.println("\t 0 - To print options.");
        System.out.println("\t 1 - To print a list of contacts.");
        System.out.println("\t 2 - To add a contact.");
        System.out.println("\t 3 - To modify a contact.");
        System.out.println("\t 4 - To remove a contact.");
        System.out.println("\t 5 - To search for a contact.");
        System.out.println("\t 6 - To quit.");
    }
}
