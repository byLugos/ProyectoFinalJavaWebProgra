package com.example.proyectofinalv2.logic;

public class Subject {
    private String subjId;
    private String nameSubj;
    private float notaFinalSubj;


    public Subject(String subjId, String nameSubj, float notaFinalSubj) {
        this.subjId = subjId;
        this.nameSubj = nameSubj;
        this.notaFinalSubj = notaFinalSubj;
    }

    public String getSubjId() {
        return subjId;
    }

    public void setSubjId(String subjId) {
        this.subjId = subjId;
    }

    public String getNameSubj() {
        return nameSubj;
    }

    public void setNameSubj(String nameSubj) {
        this.nameSubj = nameSubj;
    }

    public float getNotaFinalSubj() {
        return notaFinalSubj;
    }

    public void setNotaFinalSubj(float notaFinalSubj) {
        this.notaFinalSubj = notaFinalSubj;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjId='" + subjId + '\'' +
                ", nameSubj='" + nameSubj + '\'' +
                ", notaFinalSubj=" + notaFinalSubj +
                '}';
    }
}
