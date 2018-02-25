package com.cjm.ms;

public class Main {
    public static void main(String[] args) {
        createDeskPhone();
    }

    public static void createDeskPhone() {
        ITelephone timsPhone;
        timsPhone = new DeskPhone(123456); // Polymorphism.
        timsPhone.powerOn();
        timsPhone.callPhone(123456);
        timsPhone.answer();
    }
}
