package com.example.ati.domain;

public class Bed extends Entity<Integer>{
    private BedType bedType;
    private String ventilation;
    private int pacientCNP;

    public Bed(Integer integer, BedType bedType, String ventilation, int pacientCNP) {
        super(integer);
        this.bedType = bedType;
        this.ventilation = ventilation;
        this.pacientCNP = pacientCNP;
    }

    public BedType getBedType() {
        return bedType;
    }

    public String getVentilation() {
        return ventilation;
    }

    public int getPacientCNP() {
        return pacientCNP;
    }

    @Override
    public String toString() {
        return "Bed{" +
                "bedType=" + bedType +
                ", ventilation='" + ventilation + '\'' +
                ", pacientCNP=" + pacientCNP +
                ", id=" + id +
                '}';
    }
}
