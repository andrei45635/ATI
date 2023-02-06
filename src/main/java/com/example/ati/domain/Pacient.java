package com.example.ati.domain;

public class Pacient extends Entity<Integer>{

    private int CNP;
    private int age;
    private String premature;
    private String diagnostic;
    private int severity;

    public Pacient(Integer integer, int CNP, int age, String premature, String diagnostic, int severity) {
        super(integer);
        this.CNP = CNP;
        this.age = age;
        this.premature = premature;
        this.diagnostic = diagnostic;
        this.severity = severity;
    }

    public int getCNP() {
        return CNP;
    }

    public int getAge() {
        return age;
    }

    public String getPremature() {
        return premature;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public int getSeverity() {
        return severity;
    }
}
