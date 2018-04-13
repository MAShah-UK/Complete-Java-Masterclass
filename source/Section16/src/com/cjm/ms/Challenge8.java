package com.cjm.ms;

// Challenge 8:
// Spot and fix the problem.

/* Threads are attempting to obtain locks in opposite order which results in a deadlock.
In this case we are over synchronising code since there aren't any race conditions.
We could have also considered ReentrantLock.tryLock() with a timeout.
* */

class Tutor {
    private Student student;

    public synchronized void setStudent(Student student) {
        this.student = student;
    }

    public synchronized void studyTime() {
        System.out.println("Tutor has arrived.");
        try {
            // Wait for student to arrive and hand in assignment.
            Thread.sleep(300);
        }
        catch (InterruptedException e) {
        }
        student.startStudy();
        System.out.println("Tutor is studying with student.");
    }

    public synchronized void getProgressReport() {
        // Get progress report.
        System.out.println("Tutor gave progress report.");
    }
}

class Student {

    private Tutor tutor;

    Student(Tutor tutor) {
        this.tutor = tutor;
    }

    public synchronized void startStudy() {
        // Study.
        System.out.println("Student is studying.");
    }

    public synchronized void handInAssignment() {
        tutor.getProgressReport();
        System.out.println("Student handed in assignment.");
    }
}