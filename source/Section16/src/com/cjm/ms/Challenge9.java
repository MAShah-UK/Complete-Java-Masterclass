package com.cjm.ms;

class NewTutor {
    private NewStudent student;

    public void setStudent(NewStudent student) {
        this.student = student;
    }

    public void studyTime() {
        synchronized (this) {
            System.out.println("Tutor has arrived.");
            synchronized (student) {
                try {
                    // Wait for student to arrive.
                    this.wait(); // Releases tutor lock.
                } catch (InterruptedException e) {
                }
                student.startStudy();
                System.out.println("Tutor is studying with student.");
            }
        }
    }

    public void getProgressReport() {
        // get progress report
        System.out.println("Tutor gave progress report.");
    }
}

class NewStudent {
    private NewTutor tutor;

    public NewStudent(NewTutor tutor) {
        this.tutor = tutor;
    }

    public void startStudy() {
        // Study.
        System.out.println("Student is studying.");
    }

    public void handInAssignment() {
        synchronized (tutor) {
            tutor.getProgressReport();
            synchronized (this) {
                System.out.println("Student handed in assignment.");
                tutor.notifyAll();
            }
        }
    }
}
