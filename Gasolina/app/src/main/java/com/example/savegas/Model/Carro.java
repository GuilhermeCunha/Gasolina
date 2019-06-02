package com.example.savegas.Model;

public class Carro {
    private int id;
    private String modelo;
    private int flex;
    private double kml1;
    private double kml2;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }


    public int getFlex() {
        return flex;
    }

    public void setFlex(int flex) {
        this.flex = flex;
    }

    public double getKml1() {
        return kml1;
    }

    public void setKml1(double kml1) {
        this.kml1 = kml1;
    }

    public double getKml2() {
        return kml2;
    }

    public void setKml2(double kml2) {
        this.kml2 = kml2;
    }
}
