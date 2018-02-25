package com.cjm.ms;

public class Main {
    public static void main(String[] args) {
        createPhones();
    }

    // Practice working with interfaces.
    public static void createPhones() {
        ITelephone timsPhone; // Can't instantiate an interface, e.g. new ITelephone().

        timsPhone = new DeskPhone(123456); // Uses polymorphism - recommended.
        timsPhone.powerOn();
        timsPhone.callPhone(123456);
        timsPhone.answer();

        timsPhone = new MobilePhone(24565); // Uses polymorphism again.
        timsPhone.powerOn();
        timsPhone.callPhone(24565);
        timsPhone.answer();
    }
}
