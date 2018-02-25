package com.cjm.ms;

public interface ITelephone {
    // Fields are public by default, and must be public.
    // Interface methods are public by default, and can't be defined.
    // Interface methods can be private, but must be defined.
    void powerOn();
    void dial(int phoneNumber);
    void answer();
    boolean callPhone(int phoneNumber);
    boolean isRinging();
}
