package com.example.ati.dto;

public class Pacient2PacientDiagnosticDTO {
    private int pacientCNP;
    private int pacientSeverity;
    private String pacientDiagnostic;

    public Pacient2PacientDiagnosticDTO(int pacientCNP, int pacientSeverity, String pacientDiagnostic) {
        this.pacientCNP = pacientCNP;
        this.pacientSeverity = pacientSeverity;
        this.pacientDiagnostic = pacientDiagnostic;
    }

    public int getPacientCNP() {
        return pacientCNP;
    }

    public int getPacientSeverity() {
        return pacientSeverity;
    }

    public String getPacientDiagnostic() {
        return pacientDiagnostic;
    }
}
