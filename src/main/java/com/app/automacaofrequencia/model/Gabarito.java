package com.app.automacaofrequencia.model;

import java.util.List;

public class Gabarito {
    private List<Double> respostas;

    public Gabarito(List<Double> respostas) {
        this.respostas = respostas;
    }

    public List<Double> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Double> respostas) {
        this.respostas = respostas;
    }
}
