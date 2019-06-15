package com.project.loginsignupform;

public class StudentIni {
    String id;
    String studentName;
    String studentRollno;

    public StudentIni() {
    }


    public StudentIni(String id, String studentName, String studentRollno) {
        this.id = id;
        this.studentName = studentName;
        this.studentRollno = studentRollno;
    }

    public String getId() {
        return id;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentRollno() {
        return studentRollno;
    }
}
